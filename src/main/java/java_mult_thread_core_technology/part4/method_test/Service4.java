package java_mult_thread_core_technology.part4.method_test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * hasQueuedThread 查询指定的线程是否在等待获取次锁定
 * 查询此线程是否要获取这把锁
 * Created by lx on 2017/9/3.
 */
public class Service4 {
    public ReentrantLock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();

    public void waitMethod() {
        try {
            lock.lock();
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class Run4 {
    public static void main(String[] args) throws InterruptedException {
        final Service4 s = new Service4();
        Runnable r = () -> {
          s.waitMethod();
        };

        Thread t1 = new Thread(r);
        t1.start();
        Thread.sleep(500);
        Thread t2 = new Thread(r);
        t2.start();
        t2.sleep(500);
        System.out.println(s.lock.hasQueuedThread(t1));
        System.out.println(s.lock.hasQueuedThread(t2));
        System.out.println(s.lock.hasQueuedThreads());
    }
}