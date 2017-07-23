package think_in_java;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 5个哲学家吃饭
 * 死锁问题
 * Created by lx on 2016/3/27.
 */
public class Philosopher implements Runnable {
    private Chopstick left;//左边的筷子
    private Chopstick right;//右边的筷子
    private final int id;//记录是第几个哲学家
    private final int ponderFactor;//思考因素
    private Random rand = new Random(47);

    public Philosopher(Chopstick left, Chopstick right, int id, int ponderFactor) {
        this.left = left;
        this.right = right;
        this.id = id;
        this.ponderFactor = ponderFactor;
    }

    public void pause() throws InterruptedException {
        if(ponderFactor == 0){
            return;
        }
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor * 250));
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()){
                System.out.println(this + " " + "thinking");
                pause();
                System.out.println(this + " " + "grabbing right");//grabbing抓夺取
                right.take();
                System.out.println(this + " " + "grabbing left");//grabbing抓夺取
                left.take();
                System.out.println("eat");
                pause();
                right.drop();//放下
                left.drop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Philosopher :" + id;
    }
}

/**
 * 筷子
 */
class Chopstick{
    private boolean token = false;//记号
    public synchronized  void take(){
        try {
            while(token){
                wait();
                token = false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void drop(){
        token = false;
        notifyAll();
    }

}

/**
 * 死锁版本
 */
class DeadLockingDiningPhilosophers{
    public static void main(String[] args) throws InterruptedException, IOException {
        int ponder = 5;//思考  决定思考多久
        if(args.length > 0){
            ponder = Integer.parseInt(args[0]);
        }

        int size = 5;//决定由几只筷子
        if(args.length > 1){
            size = Integer.parseInt(args[1]);
        }

        ExecutorService exec = Executors.newCachedThreadPool();
        Chopstick[] cp = new Chopstick[size];
        for(int i = 0;i < size;i++){
            cp[i] = new Chopstick();
        }
        for(int i = 0;i < size;i++){
            exec.execute(new Philosopher(cp[i],cp[(i + 1) % size],i,ponder));
        }

        if(args.length == 3 && args[2].equals("timeout")){
            TimeUnit.SECONDS.sleep(5);
        }else{
            System.out.println("Press 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
}

class FiexDiningPhilosophers{
    public static void main(String[] args)  throws InterruptedException, IOException {
        int ponder = 5;//思考  决定思考多久
        if(args.length > 0){
            ponder = Integer.parseInt(args[0]);
        }

        int size = 5;//决定由几只筷子
        if(args.length > 1){
            size = Integer.parseInt(args[1]);
        }

        ExecutorService exec = Executors.newCachedThreadPool();
        Chopstick[] cp = new Chopstick[size];
        for(int i = 0;i < size;i++){
            cp[i] = new Chopstick();
        }

        for(int i = 0;i < size;i++){
           if(i < (size - 1)){
               exec.execute(new Philosopher(cp[i],cp[i + 1],i,ponder));
           }else{
               exec.execute(new Philosopher(cp[0],cp[1],i,ponder));
           }
        }

        if(args.length == 3 && args[2].equals("timeout")){
            TimeUnit.SECONDS.sleep(5);
        }else{
            System.out.println("Press 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
}
