package java_mult_thread_core_technology.part4.conditon.muilt;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多个condition唤醒
 * Created by no_one on 2017/9/1.
 */
public class MyService3 {
    private Lock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();
    public Condition condition1 = lock.newCondition();

    public void awaitA() {
        try {
            lock.lock();
            System.out.println("begin awaitA 时间为" + System.currentTimeMillis() + " ThreadName=" + Thread.currentThread().getName());
            condition.await();
            System.out.println("end awaitA 时间为" + System.currentTimeMillis() + " ThreadName=" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }


    public void awaitB() {
        try {
            lock.lock();
            System.out.println("begin awaitB shi时间为" + System.currentTimeMillis() + " ThreadName=" + Thread.currentThread().getName());
            condition1.await();
            System.out.println("end awaitB shi时间为" + System.currentTimeMillis() + " ThreadName=" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signalAll() {
        try {
            lock.lock();
            System.out.println("signalAll 时间为" + System.currentTimeMillis() + " ThreadName=" + Thread.currentThread().getName());
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void signalAllB() {
        try {
            lock.lock();
            System.out.println("signalAll 时间为" + System.currentTimeMillis() + " ThreadName=" + Thread.currentThread().getName());
            condition1.signalAll();
        } finally {
            lock.unlock();
        }
    }

}

class ThreadA extends Thread {
    private MyService3 service2;

    public ThreadA(MyService3 service2) {
        this.service2 = service2;
    }

    @Override
    public void run() {
        service2.awaitA();
    }
}

class ThreadB extends Thread {
    private MyService3 service2;

    public ThreadB(MyService3 service2) {
        this.service2 = service2;
    }

    @Override
    public void run() {
        service2.awaitB();
    }
}

class Run {
    public static void main(String[] args) throws InterruptedException {
        MyService3 m3 = new MyService3();
        Thread a = new ThreadA(m3);
        a.setName("A");
        a.start();
        Thread b = new ThreadB(m3);
        b.setName("B");
        b.start();
        Thread.sleep(3000);
        m3.signalAll();
    }
}