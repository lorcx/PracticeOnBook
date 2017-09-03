package java_mult_thread_core_technology.part4.method_test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * awaitUninterruptibly 在接受到信号之前一直处于等待状态
 * 即使被中断 也一致等待
 * Created by lx on 2017/9/3.
 */
public class Service11 {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void testMethod() {
        try {
            lock.lock();
            System.out.println("wait begin");
//            condition.await();
            condition.awaitUninterruptibly();
            System.out.println("wait end");
        } finally {
            lock.unlock();
        }
    }

}

class Run11 {
    public static void main(String[] args) throws InterruptedException {
        Service11 s = new Service11();
        Runnable r = () -> {
            s.testMethod();
        };

        Thread t = new Thread(r);
        t.start();
        Thread.sleep(3000);
        t.interrupt();
    }
}
