package java_mult_thread_core_technology.part4.method_test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lx on 2017/9/3.
 */
public class Service5 {
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
            System.out.println("有没有线程在等待conditon" + lock.hasWaiters(condition) + "线程数是多少？" + lock.getWaitQueueLength(condition));
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}

class Run5{
    public static void main(String[] args) throws InterruptedException {
        final Service5 s = new Service5();
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