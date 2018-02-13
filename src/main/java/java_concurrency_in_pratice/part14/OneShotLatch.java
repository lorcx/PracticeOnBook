package java_concurrency_in_pratice.part14;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * AQS
 * Binary latch using AbstractQueuedSynchronizer.
 * @Author lx
 * @Date 2018/2/13 13:42
 */
public class OneShotLatch {
    private final Sync sync = new Sync();

    public void signal() {
        sync.releaseShared(0);
    }

    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(0);
    }

    private class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected int tryAcquireShared(int arg) {
            // 如果isopen state == 1 else fail
            return getState() == 1 ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1);
            return true;
        }
    }
}
