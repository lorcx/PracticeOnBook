package java_mult_thread_core_technology.part4.method_test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lockInterruptibly 如果未被中断则获取锁
 * Created by lx on 2017/9/3.
 */
public class Service9 {
    public ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void waitMethod() {
        try {
//            lock.lock();
            lock.lockInterruptibly();
            System.out.println("lock begin " + Thread.currentThread().getName());
            for (int i = 0; i < Integer.MAX_VALUE / 10; i++) {
                String newString = new String();
                Math.random();
            }
            System.out.println("lock end " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}

class Run9 {
    public static void main(String[] args) throws InterruptedException {
        final Service9 s = new Service9();
        Runnable r = () -> s.waitMethod();

        Thread a = new Thread(r);
        a.setName("a");
        a.start();
        Thread.sleep(500);

        Thread b = new Thread(r);
        b.setName("b");
        b.start();
        b.interrupt();
        System.out.println("main end");
    }
}
