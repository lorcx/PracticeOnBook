package java_concurrency_in_pratice.part12;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * @Author lx
 * @Date 2018/2/12 11:15
 */
public class TestBoundedBuffer {
    private static final long LOCKUP_DETECT_TIMEOUT = 1000;
    private static final int CAPACITY = 10000;
    // 阀
    private static final int THRESHOLD = 10000;

    @Test
    public void testIsEmptyWhenConstructed() {
        SemaphoreBoundedBuffer<Integer> bb = new SemaphoreBoundedBuffer<>(10);
        TestCase.assertTrue(bb.isEmpty());
        TestCase.assertFalse(bb.isFull());
    }

    @Test
    public void testFullAfterPuts() throws InterruptedException {
        SemaphoreBoundedBuffer bb = new SemaphoreBoundedBuffer(10);
        for (int i = 0; i < 10; i++) {
            bb.put(i);
        }
        TestCase.assertTrue(bb.isFull());
        TestCase.assertFalse(bb.isEmpty());
    }

    @Test
    public void testTakeBlocksWhenEmpty() {
        final SemaphoreBoundedBuffer<Integer> bb = new SemaphoreBoundedBuffer<>(10);
        Thread taker = new Thread(() -> {
            try {
                int unused = bb.take();
                TestCase.fail();// 如果走到这里会报错
            } catch (InterruptedException e) {

            }
        });

        taker.start();
        try {
            Thread.sleep(LOCKUP_DETECT_TIMEOUT);
            taker.interrupt();
            taker.join(LOCKUP_DETECT_TIMEOUT);
            TestCase.assertFalse(taker.isAlive());
        } catch (InterruptedException e) {
            TestCase.fail();
        }
    }

    class Big {
        double[] data = new double[100000];
    }

    /**
     * Leak泄漏
     */
    @Test
    public void testLeak() throws InterruptedException {
        SemaphoreBoundedBuffer<Big> bb = new SemaphoreBoundedBuffer<>(CAPACITY);
        int heapSize1 = snapshotHeap();
        for (int i = 0; i < CAPACITY; i++) {
            bb.put(new Big());
        }
        for (int i = 0; i < CAPACITY; i++) {
            bb.take();
        }
        int heapSize2 = snapshotHeap();
        TestCase.assertTrue(heapSize1 - heapSize2 < THRESHOLD);
    }

    private int snapshotHeap() {
        return 0;
    }
}
