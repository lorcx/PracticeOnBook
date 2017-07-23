package think_in_java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * wait 和notify notify all
 * wait 会释放锁 sleep不会 他必须在时间结束或notify ..才能被唤醒
 *
 *
 * #############
 * ####一定要把while放到try catch 中 否则不能结束循环#####
 * ###########
 * Created by lx on 2016/3/26.
 */
public class Car {
    private boolean WaxOn = false;//是否上蜡

    /**
     * 上蜡
     */
    public synchronized void waxed(){
        WaxOn = true;
        notifyAll();
    }

    /**
     * 抛光
     */
    public synchronized void buffed(){
        WaxOn = false;
        notifyAll();
    }

    /**
     * 等待上蜡
     */
    public synchronized void waitForWaxing() throws InterruptedException {
        while(WaxOn == false){
            wait();
        }
    }

    /**
     * 等待抛光
     */
    public synchronized void waitForBuffing() throws InterruptedException {
        while(WaxOn == true){
            wait();
        }
    }
}

class WaxOn implements Runnable {
    private Car car;

    public WaxOn(Car car) {
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


class WaxOff implements Runnable {
    private Car car;

    public WaxOff(Car car) {
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


class WaxMatic{
    public static void main(String[] args) throws InterruptedException {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car));

        TimeUnit.SECONDS.sleep(5);//5s

        exec.shutdownNow();

    }
}