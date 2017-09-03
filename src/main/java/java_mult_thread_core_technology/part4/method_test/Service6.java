package java_mult_thread_core_technology.part4.method_test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lx on 2017/9/3.
 */
public class Service6 {
    private ReentrantLock lock;

    public Service6(boolean isFair) {
        this.lock = new ReentrantLock(isFair);
    }

    public void serviceMethod() {
        lock.lock();
        System.out.println("公平锁情况：" + lock.isFair());
        lock.unlock();
    }

}

class Run6 {
    public static void main(String[] args) {
        final Service6 s = new Service6(true);
        Runnable r = () -> s.serviceMethod();
        new Thread(r).start();
        final Service6 s2 = new Service6(false);
        Runnable r2 = () -> s2.serviceMethod();
        new Thread(r2).start();

    }
}