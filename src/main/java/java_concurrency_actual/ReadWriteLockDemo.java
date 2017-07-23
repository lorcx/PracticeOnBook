package java_concurrency_actual;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * 读读之间不阻塞
 * 当读操作多余写操作性能提升明显
 * Created by Administrator on 2017/5/4 0004.
 */
public class ReadWriteLockDemo {
    // 普通重写锁
    private static Lock lock = new ReentrantLock();
    // 读写锁
    private static ReentrantReadWriteLock readWritelock = new ReentrantReadWriteLock();
    private static Lock readLock = readWritelock.readLock();
    private static Lock writeLock = readWritelock.writeLock();
    private int value;


    public Object handleRead(Lock lock) throws InterruptedException {
        // 模拟读操作
        try {
            lock.lock();
            Thread.sleep(1000);
            return value;
        } finally {
            lock.unlock();
        }
    }


    public void handleWrite(Lock lock, int index) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            value = index;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final ReadWriteLockDemo demo = new ReadWriteLockDemo();

        Runnable readRunnable = () -> {
            try {
                // 读写锁2s左右完成 lock 需要8s左右
                demo.handleRead(readLock);
//                demo.handleRead(lock);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable writeRunnable = () -> {
            try {
                demo.handleWrite(writeLock, new Random().nextInt());
//                demo.handleWrite(lock, new Random().nextInt());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 18; i++) {
            new Thread(readRunnable).start();
        }

        for (int i = 18; i < 20; i++) {
            new Thread(writeRunnable).start();
        }
    }

}
