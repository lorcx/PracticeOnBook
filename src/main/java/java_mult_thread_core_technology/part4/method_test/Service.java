package java_mult_thread_core_technology.part4.method_test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lx on 2017/9/3.
 */
public class Service {
    private ReentrantLock lock = new ReentrantLock();

    public void serviceMethod1() {
        try {
            lock.lock();
            System.out.println("serviceMethod1 getHoldCount=" + lock.getHoldCount());
            serviceMethod2();
        } finally {
            lock.unlock();
        }
    }

    public void serviceMethod2() {
        try {
            lock.lock();
            System.out.println("serviceMethod2 getHoldCount=" + lock.getHoldCount());
        } finally {
            lock.unlock();
        }
    }
}

class Run {
    public static void main(String[] args) {
        Service s = new Service();
        s.serviceMethod1();
    }
}