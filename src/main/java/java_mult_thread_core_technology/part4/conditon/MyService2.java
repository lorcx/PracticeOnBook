package java_mult_thread_core_technology.part4.conditon;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by no_one on 2017/9/1.
 */
public class MyService2 {
    private Lock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();

    public void await() {
        try {
            lock.lock();
            System.out.println("await 时间" + System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signal() {
        try {
            lock.lock();
            System.out.println("signal 时间为" + System.currentTimeMillis());
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class ThreadAa extends Thread {
    private MyService2 service2;

    public ThreadAa(MyService2 service2) {
        this.service2 = service2;
    }

    @Override
    public void run() {
        service2.await();
    }
}

class RunService2{
    public static void main(String[] args) throws InterruptedException {
        MyService2 service2 = new MyService2();
        ThreadAa a = new ThreadAa(service2);
        a.start();
        Thread.sleep(3000);
        service2.signal();
    }
}

