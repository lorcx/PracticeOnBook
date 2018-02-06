package java_concurrency_in_pratice.part8.puzzle;

import java_concurrency_in_pratice.anno.ThreadSafe;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁
 * @Author: lx
 * @Date: Created in 2018/2/6 0006
 */
@ThreadSafe
public class ValueLatch<T> {
    private T value = null;
    private final CountDownLatch done = new CountDownLatch(1);

    public boolean isSet() {
        return done.getCount() == 0;
    }

    public synchronized void setValue(T newValue) {
        if (!isSet()) {
            value = newValue;
            done.countDown();
        }
    }

    public T getValue() throws InterruptedException {
        // 阻塞
        done.await();
        synchronized (this) {
            return value;
        }
    }

}
