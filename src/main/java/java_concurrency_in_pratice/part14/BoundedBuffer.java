package java_concurrency_in_pratice.part14;

/**
 * @Author lx
 * @Date 2018/2/13 12:57
 */
public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {
    public BoundedBuffer() {
        this(100);
    }

    public BoundedBuffer(int capacity) {
        super(capacity);
    }

    public synchronized void put(V v) throws InterruptedException {
        while (isFull()) {
            wait();
        }
        doPut(v);
        notifyAll();
    }

    /**
     *条件通知
     * @param v
     * @throws InterruptedException
     */
    public synchronized void alternatePut(V v) throws InterruptedException {
        while (isFull()) {
            wait();
        }
        boolean wasEmpty = isEmpty();
        doPut(v);
        if (wasEmpty) {
            notifyAll();
        }
    }

    public synchronized V take() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }
        V v = doTake();
        notifyAll();
        return v;
    }



}
