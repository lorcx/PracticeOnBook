package think_in_java;//: concurrency/Car2Builder.java
// A complex example of tasks working together.
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

public class Car2 {
    private final int id;
    private boolean
            engine = false, driveTrain = false, wheels = false;
    public Car2(int idn)  { id = idn; }
    // Empty Car2 object:
    public Car2()  { id = -1; }
    public synchronized int getId() { return id; }
    public synchronized void addEngine() { engine = true; }
    public synchronized void addDriveTrain() {
        driveTrain = true;
    }
    public synchronized void addWheels() { wheels = true; }
    public synchronized String toString() {
        return "Car2 " + id + " [" + " engine: " + engine
                + " driveTrain: " + driveTrain
                + " wheels: " + wheels + " ]";
    }
}

class Car2Queue extends LinkedBlockingQueue<Car2> {}

class ChassisBuilder implements Runnable {
    private Car2Queue Car2Queue;
    private int counter = 0;
    public ChassisBuilder(Car2Queue cq) { Car2Queue = cq; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(500);
                // Make chassis:
                Car2 c = new Car2(counter++);
                System.out.println("ChassisBuilder created " + c);
                // Insert into queue
                Car2Queue.put(c);
            }
        } catch(InterruptedException e) {
            System.out.println("Interrupted: ChassisBuilder");
        }
        System.out.println("ChassisBuilder off");
    }
}

class Assembler implements Runnable {
    private Car2Queue chassisQueue, finishingQueue;
    private Car2 Car2;
    private CyclicBarrier barrier = new CyclicBarrier(4);
    private RobotPool robotPool;
    public Assembler(Car2Queue cq, Car2Queue fq, RobotPool rp){
        chassisQueue = cq;
        finishingQueue = fq;
        robotPool = rp;
    }
    public Car2 Car2() { return Car2; }
    public CyclicBarrier barrier() { return barrier; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                // Blocks until chassis is available:
                Car2 = chassisQueue.take();
                // Hire robots to perform work:
                robotPool.hire(EngineRobot.class, this);
                robotPool.hire(DriveTrainRobot.class, this);
                robotPool.hire(WheelRobot.class, this);
                barrier.await(); // Until the robots finish
                // Put Car2 into finishingQueue for further work
                finishingQueue.put(Car2);
            }
        } catch(InterruptedException e) {
            System.out.println("Exiting Assembler via interrupt");
        } catch(BrokenBarrierException e) {
            // This one we want to know about
            throw new RuntimeException(e);
        }
        System.out.println("Assembler off");
    }
}

class Reporter implements Runnable {
    private Car2Queue Car2Queue;
    public Reporter(Car2Queue cq) { Car2Queue = cq; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                System.out.println(Car2Queue.take());
            }
        } catch(InterruptedException e) {
            System.out.println("Exiting Reporter via interrupt");
        }
        System.out.println("Reporter off");
    }
}

abstract class Robot implements Runnable {
    private RobotPool pool;
    public Robot(RobotPool p) { pool = p; }
    protected Assembler assembler;
    public Robot assignAssembler(Assembler assembler) {
        this.assembler = assembler;
        return this;
    }
    private boolean engage = false;
    public synchronized void engage() {
        engage = true;
        notifyAll();
    }
    // The part of run() that's different for each robot:
    abstract protected void performService();
    public void run() {
        try {
            powerDown(); // Wait until needed
            while(!Thread.interrupted()) {
                performService();
                assembler.barrier().await(); // Synchronize
                // We're done with that job...
                powerDown();
            }
        } catch(InterruptedException e) {
            System.out.println("Exiting " + this + " via interrupt");
        } catch(BrokenBarrierException e) {
            // This one we want to know about
            throw new RuntimeException(e);
        }
        System.out.println(this + " off");
    }
    private synchronized void
    powerDown() throws InterruptedException {
        engage = false;
        assembler = null; // Disconnect from the Assembler
        // Put ourselves back in the available pool:
        pool.release(this);
        while(engage == false)  // Power down
            wait();
    }
    public String toString() { return getClass().getName(); }
}

class EngineRobot extends Robot {
    public EngineRobot(RobotPool pool) { super(pool); }
    protected void performService() {
        System.out.println(this + " installing engine");
        assembler.Car2().addEngine();
    }
}

class DriveTrainRobot extends Robot {
    public DriveTrainRobot(RobotPool pool) { super(pool); }
    protected void performService() {
        System.out.println(this + " installing DriveTrain");
        assembler.Car2().addDriveTrain();
    }
}

class WheelRobot extends Robot {
    public WheelRobot(RobotPool pool) { super(pool); }
    protected void performService() {
        System.out.println(this + " installing Wheels");
        assembler.Car2().addWheels();
    }
}

class RobotPool {
    // Quietly prevents identical entries:
    private Set<Robot> pool = new HashSet<Robot>();
    public synchronized void add(Robot r) {
        pool.add(r);
        notifyAll();
    }
    public synchronized void
    hire(Class<? extends Robot> robotType, Assembler d)
            throws InterruptedException {
        for(Robot r : pool)
            if(r.getClass().equals(robotType)) {
                pool.remove(r);
                r.assignAssembler(d);
                r.engage(); // Power it up to do the task
                return;
            }
        wait(); // None available
        hire(robotType, d); // Try again, recursively
    }
    public synchronized void release(Robot r) { add(r); }
}

class Car2Builder {
    public static void main(String[] args) throws Exception {
        Car2Queue chassisQueue = new Car2Queue(),
                finishingQueue = new Car2Queue();
        ExecutorService exec = Executors.newCachedThreadPool();
        RobotPool robotPool = new RobotPool();
        exec.execute(new EngineRobot(robotPool));
        exec.execute(new DriveTrainRobot(robotPool));
        exec.execute(new WheelRobot(robotPool));
        exec.execute(new Assembler(
                chassisQueue, finishingQueue, robotPool));
        exec.execute(new Reporter(finishingQueue));
        // Start everything running by producing chassis:
        exec.execute(new ChassisBuilder(chassisQueue));
        TimeUnit.SECONDS.sleep(7);
        exec.shutdownNow();
    }
} /* (Execute to see output) *///:~
