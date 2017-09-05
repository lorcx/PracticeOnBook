package java_mult_thread_core_technology.part4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by no_one on 2017/9/1.
 */
public class MyService2 {
    private Lock lock = new ReentrantLock();

    public void methodA() {
        try {
            lock.lock();
            System.out.println("methodA begin thread name = " + Thread.currentThread().getName() + " time = " + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("methodA begin thread name = " + Thread.currentThread().getName() + " time = " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void methodB() {
        try {
            lock.lock();
            System.out.println("methodB begin thread name = " + Thread.currentThread().getName() + " time = " + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("methodB begin thread name = " + Thread.currentThread().getName() + " time = " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class ThreadA extends Thread{

    private MyService2 service;

    public ThreadA(MyService2 service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.methodA();
    }
}

class ThreadAA extends Thread{

    private MyService2 service;

    public ThreadAA(MyService2 service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.methodA();
    }
}

class ThreadB extends Thread{

    private MyService2 service;

    public ThreadB(MyService2 service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.methodB();
    }
}

class ThreadBB extends Thread{

    private MyService2 service;

    public ThreadBB(MyService2 service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.methodB();
    }
}

class RunService2 {
    public static void main(String[] args) throws InterruptedException {
        MyService2 service = new MyService2();
        ThreadA a = new ThreadA(service);
        a.setName("A");
        a.start();
        ThreadAA aa = new ThreadAA(service);
        aa.setName("aa");
        aa.start();

        Thread.sleep(100);

        ThreadB b = new ThreadB(service);
        b.setName("B");
        b.start();
        ThreadBB bb = new ThreadBB(service);
        bb.setName("BB");
        bb.start();

    }
}