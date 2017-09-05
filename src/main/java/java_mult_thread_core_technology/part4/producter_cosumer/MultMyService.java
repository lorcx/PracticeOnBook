package java_mult_thread_core_technology.part4.producter_cosumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by no_one on 2017/9/1.
 */
public class MultMyService {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean hasValue;

    public void set() {
        try {
            lock.lock();
            while (hasValue) {
                System.out.println("连续生产。。。");
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
                System.out.println("连续消费。。。");
                condition.await();
            }
            System.out.println("消费了1个");
            hasValue = false;
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}

class MyThreadA1 extends Thread{
    private MultMyService myService;

    public MyThreadA1(MultMyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        while (true) {
            myService.set();
        }
    }
}


class MyThreadB2 extends Thread{
    private MultMyService myService;

    public MyThreadB2(MultMyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        while (true) {
            myService.get();
        }
    }
}

class RunMultMyService {
    public static void main(String[] args) {
        MultMyService ms = new MultMyService();
        MyThreadA1[] t1 = new MyThreadA1[10];
        MyThreadB2[] t2 = new MyThreadB2[10];
        for (int i = 0; i < 10; i++) {
            t1[i] = new MyThreadA1(ms);
            t2[i] = new MyThreadB2(ms);
            t1[i].start();
            t2[i].start();
        }

    }
}