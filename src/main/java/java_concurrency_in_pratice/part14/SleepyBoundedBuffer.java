package java_concurrency_in_pratice.part14;

/**
 * 使用轮训和睡眠阻塞
 * @Author lx
 * @Date 2018/2/13 12:53
 */
public class SleepyBoundedBuffer<V> extends BaseBoundedBuffer<V> {

    int SLEEP_GRANULARITY = 60;

    protected SleepyBoundedBuffer(int capacity) {
        super(capacity);
    }

    public SleepyBoundedBuffer() {
        this(100);
    }

    public void put(V v) throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (!isFull()) {
                    doPut(v);
                    return;
                }
            }
            Thread.sleep(SLEEP_GRANULARITY);
        }
    }

    public V take(V v) throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (!isEmpty()) {
                    return doTake();
                }
            }
            Thread.sleep(SLEEP_GRANULARITY);
        }
    }
}
