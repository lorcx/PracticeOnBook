package java_concurrency_in_pratice.part14;

import java_concurrency_in_pratice.anno.ThreadSafe;

/**
 * 如果不满足条件抛异常，客户端需要自己处理异常并且重试
 * @Author lx
 * @Date 2018/2/13 12:45
 */
@ThreadSafe
public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V> {
    public GrumpyBoundedBuffer() {
        this(100);
    }

    protected GrumpyBoundedBuffer(int capacity) {
        super(capacity);
    }

    public synchronized void put(V v) throws BufferFullException {
        if (isFull()) {
            throw new BufferFullException();
        }
        doPut(v);
    }

    public synchronized V take() throws BufferEmptyException {
        if (isEmpty()) {
            throw new BufferEmptyException();
        }
        return doTake();
    }

    class EampleUsage {
        private GrumpyBoundedBuffer<String> buffer;
        int SLEEP_GRANULARITY = 50;

        void userBuffer() throws InterruptedException {
            while (true) {
                try {
                    String item = buffer.take();
                    break;
                } catch (BufferEmptyException e) {
                    Thread.sleep(SLEEP_GRANULARITY);
                }
            }
        }
    }
}
