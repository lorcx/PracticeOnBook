package java_concurrency_in_pratice.part12;

import java_concurrency_in_pratice.anno.ThreadSafe;

import java.util.concurrent.Semaphore;

/**
 * @Author lx
 * @Date 2018/2/12 10:39
 */
@ThreadSafe
public class SemaphoreBoundedBuffer<E> {
    // availableItems 可从缓存中删除的元素个数，availableSpaces可往缓存中添加到元素个数
    private final Semaphore availableItems, availableSpaces;
    private final E[] items;
    private int putPosition, takePosition = 0;

    public SemaphoreBoundedBuffer(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        availableItems = new Semaphore(0);
        availableSpaces = new Semaphore(capacity);
        items = (E[])new Object[capacity];
    }

    public boolean isEmpty() {
        return availableItems.availablePermits() == 0;
    }

    public boolean isFull() {
        return availableSpaces.availablePermits() == 0;
    }

    public void put(E x) throws InterruptedException {
        // 拿许可 相当于删除一个许可
        availableSpaces.acquire();
        doInsert(x);
        // 归还许可 相当于创建一个许可给你
        availableItems.release();
    }

    private void doInsert(E x) {
        int i = putPosition;
        items[i] = x;
        putPosition = (++i == items.length ? 0 : i);
    }

    public E take() throws InterruptedException {
        // acquire 是阻塞方法得放前边
        availableItems.acquire();
        E e = doExtact();
        availableSpaces.release();
        return e;
    }

    private E doExtact() {
        int i = takePosition;
        E e = items[i];
        items[i] = null;
        takePosition = (++i == items.length ? 0 : i);
        return e;
    }
}
