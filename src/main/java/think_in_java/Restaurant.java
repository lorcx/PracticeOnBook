package think_in_java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 生产者和消费者
 * Restaurant饭店
 * Created by lx on 2016/3/26.
 */
public class Restaurant {
    Meal1 Meal1;
    ExecutorService exec = Executors.newCachedThreadPool();
    WaitPeron wp = new WaitPeron(this);
    Chef chef = new Chef(this);
    public Restaurant() {
        exec.execute(wp);
        exec.execute(chef);
    }

    public static void main(String[] args) {
        new Restaurant();
    }
}

/**
 * 消费者 服务员
 */
class WaitPeron implements Runnable {
    private Restaurant rest;
    public WaitPeron(Restaurant rest) {
        this.rest = rest;
    }

    @Override
    public void run() {
        try {//保证能退出循环在while外边
            while (!Thread.interrupted()){
                synchronized (this) {
                    while(rest.Meal1 == null){//食物还没好
                        wait();
                    }
                }

                System.out.println("waitperson got " + rest.Meal1);

                synchronized (rest.chef){
                    rest.Meal1 = null;//消费食物
                    rest.chef.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("waitperson interrupted");
        }
    }
}

/**
 * 生产者 厨师
 */
class Chef implements Runnable {
    private Restaurant rest;
    private int count = 0;

    public Chef(Restaurant rest) {
        this.rest = rest;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()){
                synchronized (this){
                    while(rest.Meal1 != null){
                        wait();
                    }
                }

                if(++count == 10){
                    System.out.println("out of food,closing");
                    rest.exec.shutdownNow();
                }

                System.out.println("order up!");

                synchronized (rest.wp){//拿到这个对象上的锁
                    rest.Meal1 = new Meal1(count);
                    rest.wp.notifyAll();
                }

                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("chef interrupted");
        }

    }
}

class Meal1{
    private final int orderNum;

    public Meal1(int orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "meal " + orderNum;
    }
}