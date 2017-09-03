package java_mult_thread_core_technology.part4.method_test;

import java.util.Calendar;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * awaitUntil 比 await 多了一个功能 最后期限
 * 它们两个都是接受到信号、被中断前阻止线程继续执行
 * awaitUninterruptibly 是只有接受到信号才停止中断
 * Created by lx on 2017/9/3.
 */
public class Service12 {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void waitMethod() {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.SECOND, 10);
            lock.lock();
            System.out.println("wait begin timer: " + System.currentTimeMillis());
            condition.awaitUntil(calendar.getTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void notifyMethod() {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.SECOND, 10);
            lock.lock();
            System.out.println("notify begin timer: " + System.currentTimeMillis());
            condition.signalAll();
            System.out.println("notify end timer: " + System.currentTimeMillis());
        } finally {
            lock.unlock();
        }
    }
}

class Run12 {
    public static void main(String[] args) throws InterruptedException {
        Service12 s = new Service12();
        Runnable r = () -> {
            s.waitMethod();
        };

        Runnable r2 = () -> {
            s.notifyMethod();
        };

        Thread t = new Thread(r);
        t.start();
        Thread.sleep(2000);

        Thread t2 = new Thread(r2);
        t2.start();
    }
}
