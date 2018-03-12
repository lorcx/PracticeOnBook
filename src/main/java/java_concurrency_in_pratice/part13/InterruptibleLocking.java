package java_concurrency_in_pratice.part13;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 获取可以被中断的锁
 * @Author lx
 * @Date 2018/2/12 21:42
 */
public class InterruptibleLocking {
    private Lock lock = new ReentrantLock();

    public boolean sendOnSharedLine(String message) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            return cancellableSendOnSharedLine(message);
        } finally {
            lock.unlock();
        }

    }

    private boolean cancellableSendOnSharedLine(String message) {
        return false;
    }
}
