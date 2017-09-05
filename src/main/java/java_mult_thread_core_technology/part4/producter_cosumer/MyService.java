package java_mult_thread_core_technology.part4.producter_cosumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1 v 1生产->消费
 * Created by no_one on 2017/9/1.
 */
public class MyService {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean hasValue;

    public void set() {
        try {
            lock.lock();
            while (hasValue) {
                condition.await();
            }
            System.out.println("生产了1个");
            hasValue = true;
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void get() {
        try {
            lock.lock();
            while (!hasValue) {
                condition.await();
            }
            System.out.println("消费了1个");
            hasValue = false;
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }


}

class MyThread1 extends Thread{
    private MyService myService;

    public MyThread1(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        while (true) {
            myService.set();
        }
    }
}


class MyThread2 extends Thread{
    private MyService myService;

    public MyThread2(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        while (true) {
            myService.get();
        }
    }
}

class RunPm {
    public static void main(String[] args) {
        MyService ms = new MyService();
        MyThread1 t1 = new MyThread1(ms);
        MyThread2 t2 = new MyThread2(ms);
        t1.start();
        t2.start();
    }
}