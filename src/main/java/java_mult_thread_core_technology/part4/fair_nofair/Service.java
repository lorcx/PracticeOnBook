package java_mult_thread_core_technology.part4.fair_nofair;

import java.util.concurrent.locks.ReentrantLock;

/**
 * reent重入
 * Created by lx on 2017/9/3.
 */
public class Service {
    private ReentrantLock lock;

    public Service(boolean isFair) {
        this.lock = new ReentrantLock(isFair);
    }

    public void serviceMethod() {
        try {
            lock.lock();
            System.out.println("ThreadName=" + Thread.currentThread().getName() + " 获得锁定");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class RunFair {
    public static void main(String[] args) {
        final Service s = new Service(true);
        Runnable r = () -> {
            System.out.println("线程⭐ " + Thread.currentThread().getName() + "运行了");
            s.serviceMethod();
        };

        Thread[] threadArray = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threadArray[i] = new Thread(r);
        }

        for (int i = 0; i < 10; i++) {
            threadArray[i].start();
        }


    }
}

class RunNoFair {
    public static void main(String[] args) {
        final Service s = new Service(false);
        Runnable r = () -> {
            System.out.println("线程⭐ " + Thread.currentThread().getName() + "运行了");
            s.serviceMethod();
        };

        Thread[] threadArray = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threadArray[i] = new Thread(r);
        }

        for (int i = 0; i < 10; i++) {
            threadArray[i].start();
        }


    }
}