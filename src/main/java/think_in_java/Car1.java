package think_in_java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * (重写 car类  用显示锁和codition)
 * wait 和notify notify all
 * wait 会释放锁 sleep不会 他必须在时间结束或notify ..才能被唤醒
 *
 *
 * #############
 * ####一定要把while放到try catch 中 否则不能结束循环#####
 * ###########
 * Created by lx on 2016/3/26.
 */
public class Car1 {
    private boolean WaxOn = false;//是否上蜡
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    /**
     * 上蜡
     */
    public void waxed(){
        lock.lock();
        try {
            WaxOn = true;
            condition.signalAll();
        } finally {
            lock.unlock();//保证任何情况下都能解锁
        }
    }

    /**
     * 抛光
     */
    public void buffed(){
        lock.lock();
        try {
            WaxOn = false;
            condition.signalAll();
        } finally {
            lock.unlock();//保证任何情况下都能解锁
        }
    }

    /**
     * 等待上蜡
     */
    public void waitForWaxing() throws InterruptedException {
        lock.lock();
        try {
            while(WaxOn == false){
               condition.await();
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 等待抛光
     */
    public void waitForBuffing() throws InterruptedException {
        lock.lock();
        try {
            while(WaxOn == true){
                condition.await();
            }
        } finally {
            lock.unlock();//一定要先上锁
        }
    }
}

class WaxOn1 implements Runnable {
    private Car1 car;

    public WaxOn1(Car1 car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()){
                System.out.println("WaxOn...");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax On task");
    }
}


class WaxOff1 implements Runnable {
    private Car1 car;

    public WaxOff1(Car1 car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()){
                System.out.println("WaxOff begin...");
                car.waitForWaxing();//等待上蜡
                System.out.println("waxOn off");
                TimeUnit.MILLISECONDS.sleep(200);//模拟
                car.buffed();//抛光
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("WaxOff end...");
    }
}


class WaxMatic1{
    public static void main(String[] args) throws InterruptedException {
        Car1 car = new Car1();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff1(car));
        exec.execute(new WaxOn1(car));

        TimeUnit.SECONDS.sleep(5);//5s

        exec.shutdownNow();

    }
}