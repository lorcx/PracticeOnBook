package java_concurrency_in_pratice.part12;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author lx
 * @Date 2018/2/12 14:12
 */
public class TestThreadPool {
    private final TestingThreadFactory threadFactory = new TestingThreadFactory();

    @Test
    public void testPoolExpansion() throws InterruptedException {
        int MAX_SIZE = 10;
        ThreadPoolExecutor exec = (ThreadPoolExecutor) Executors.newFixedThreadPool(MAX_SIZE);
        exec.setThreadFactory(threadFactory);
        for (int i = 0; i < 10 * MAX_SIZE; i++) {
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(Long.MAX_VALUE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        for (int i = 0; i < 20 && threadFactory.numCreated.get() < MAX_SIZE; i++) {
            Thread.sleep(200);
        }
        TestCase.assertEquals(threadFactory.numCreated.get(), MAX_SIZE);
        exec.shutdown();
    }

    /**
     * 装饰器
     */
    class TestingThreadFactory implements ThreadFactory {
        public final AtomicInteger numCreated = new AtomicInteger();
        private final ThreadFactory factory = Executors.defaultThreadFactory();

        @Override
        public Thread newThread(Runnable r) {
            numCreated.incrementAndGet();
            return factory.newThread(r);
        }
    }

}
