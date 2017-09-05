package java_mult_thread_core_technology.part4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by no_one on 2017/9/1.
 */
public class MyService {
    private Lock lock = new ReentrantLock();

    public void testMethod() {
        lock.lock();
        for (int i = 0; i < 5; i++) {
            System.out.println("thread name = " + Thread.currentThread().getName());
        }
        lock.unlock();
    }
}

class MyThread extends Thread{
    private MyService myService;

    public MyThread(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.testMethod();
    }
}

class RunLock1 {

    public static void main(String[] args) {
        MyService ms = new MyService();
        Thread t = new MyThread(ms);
        Thread t2 = new MyThread(ms);
        Thread t3 = new MyThread(ms);
        Thread t4 = new MyThread(ms);
        Thread t5 = new MyThread(ms);
        t.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}