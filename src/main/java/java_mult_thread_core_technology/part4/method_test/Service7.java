package java_mult_thread_core_technology.part4.method_test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * isHeldByCurrentThread 判断当前是否被锁定
 * 检查当前线程是否保持此锁定
 * Created by lx on 2017/9/3.
 */
public class Service7 {
    private ReentrantLock lock;

    public Service7(boolean isFair) {
        this.lock = new ReentrantLock(isFair);
    }

    public void serviceMethod() {
        try {
            System.out.println(lock.isHeldByCurrentThread());
            lock.lock();
            System.out.println(lock.isHeldByCurrentThread());
        } finally {
            lock.unlock();
        }
    }
}

class Run7 {
    public static void main(String[] args) {
        final Service7 s = new Service7(true);
        Runnable r = () -> {
          s.serviceMethod();
        };

        Thread t = new Thread(r);
        t.start();
    }
}
