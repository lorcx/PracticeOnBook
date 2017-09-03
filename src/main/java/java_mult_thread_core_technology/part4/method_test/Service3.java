package java_mult_thread_core_technology.part4.method_test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * getWaitQueueLength
 * 返回等待与此锁定相关条件conditon的线程估计数
 * Created by lx on 2017/9/3.
 */
public class Service3 {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void waitMethod() {
        try {
            lock.lock();
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void notifyMethod() {
        try {
            lock.lock();
            System.out.println("有" + lock.getWaitQueueLength(condition) + "个线程正在等待conditon");
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}

class Run3 {
    public static void main(String[] args) throws InterruptedException {
        final Service3 s = new Service3();
        Runnable r = () -> {
            s.waitMethod();
        };


        Thread[] threadArray = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threadArray[i] = new Thread(r);
        }

        for (int i = 0; i < 10; i++) {
            threadArray[i].start();
        }

        Thread.sleep(2000);
        s.notifyMethod();
    }
}
