package java_mult_thread_core_technology.part4.method_test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * isLocked 查询是否有任意线程持有此锁
 * Created by lx on 2017/9/3.
 */
public class Service8 {
    private ReentrantLock lock;

    public Service8(boolean isFair) {
        this.lock = new ReentrantLock(isFair);
    }

    public void serviceMethod() {
        try {
            System.out.println(lock.isLocked());
            lock.lock();
            System.out.println(lock.isLocked());
        } finally {
            lock.unlock();
        }
    }
}

class Run8 {
    public static void main(String[] args) {
        final Service8 s = new Service8(true);
        Runnable r = () -> {
          s.serviceMethod();
        };

        Thread t = new Thread(r);
        t.start();
    }
}