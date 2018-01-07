package java_concurrency_in_pratice.part4;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁
 *
 * @Author: lx
 * @Date: Created in 2018/1/7 0007
 */
public class TestHarness {
    /**
     * 使用闭锁而不是立即启动是为了测试并发执行任务所需要的时间，
     * 如果直接启动某些线程就会“领先”执行，测试结果不准。
     * @param nThreads
     * @param task
     * @return
     * @throws InterruptedException
     */
    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        // await方法是等待countdownlatch的计数器为0才继续执行，否则阻塞。
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            // countDown是将计时器减1
                            endGate.countDown();
                        }
                    } catch (InterruptedException ignored) {

                    }
                }
            };
            t.start();
        }

        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end -start;
    }
}
