package java_mult_thread_core_technology.part4.read_write_lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写互斥
 * Created by lx on 2017/9/3.
 */
public class Service2 {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void read() {
        try {
            try {
                lock.readLock().lock();
                System.out.println("获得读锁" + Thread.currentThread().getName() + System.currentTimeMillis());
                Thread.sleep(10000);
            } finally {
                lock.readLock().unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void write() {
        try {
            try {
                lock.writeLock().lock();
                System.out.println("获得写锁" + Thread.currentThread().getName() + System.currentTimeMillis());
                Thread.sleep(10000);
            } finally {
                lock.writeLock().unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class Run2 {
    public static void main(String[] args) throws InterruptedException {
        Service2 s = new Service2();
        Runnable r = () -> {
            s.read();
        };

        Runnable r2 = () -> {
            s.write();
        };

        Thread t = new Thread(r);
        t.start();
        Thread t2 = new Thread(r2);
        t2.start();
    }
}
