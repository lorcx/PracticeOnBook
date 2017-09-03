package java_mult_thread_core_technology.part4.method_test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * tryLock 调用时没有被另一个线程锁定，才获取锁。
 * Created by lx on 2017/9/3.
 */
public class Service10 {
    public ReentrantLock lock = new ReentrantLock();
    public void waitMethod() {
//
//        if (lock.isHeldByCurrentThread()) {
//            System.out.println(" 获取锁");
//        } else {
//            System.out.println(" 没有获取锁");
//        }

//        lock.lock();
//        lock.lock();

//        System.out.println( lock.tryLock());
//        System.out.println( lock.tryLock());
//        System.out.println(lock.getHoldCount());
//        System.out.println(lock.getQueueLength());

//        if (lock.tryLock()) {
//            System.out.println(Thread.currentThread().getName() + " 获取锁");
//        } else {
//            System.out.println(Thread.currentThread().getName() + " 没有获取锁");
//        }

        try {
            if (lock.tryLock(1/2, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName() + "获得锁的时间:" + System.currentTimeMillis());
            } else {
                System.out.println(Thread.currentThread().getName() + "没有获得锁");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}

class Run10 {
    public static void main(String[] args) {
        final Service10 s = new Service10();
        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName() + ":" + System.currentTimeMillis());
            s.waitMethod();
        };

        Thread t1 = new Thread(r);
        t1.setName("a");
        t1.start();

        Thread t2 = new Thread(r);
        t2.setName("b");
        t2.start();



    }
}


