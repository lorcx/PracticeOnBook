package java_concurrency_in_pratice.part4;

import java_concurrency_in_pratice.anno.ThreadSafe;

/**
 * @Author lx
 * @Date 2018/1/5 22:11
 */
@ThreadSafe
public class Counter {
    private long value = 0;

    public synchronized long getValue() {
        return value;
    }

    public synchronized long increment() {
        if (value == Long.MAX_VALUE) {
            throw new IllegalStateException("counter 溢出");
        }
        return ++value;
    }
}
