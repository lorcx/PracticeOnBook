package java_concurrency_in_pratice.part12;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author lx
 * @Date 2018/2/12 13:32
 */
public class PubTakeTest {
    protected static final ExecutorService pool = Executors.newCachedThreadPool();
    protected CyclicBarrier barrier;// 保证多个线程并发运行
    protected final SemaphoreBoundedBuffer<Integer> bb;
    protected final int nTrials, nPairs;
    protected final AtomicInteger putSum = new AtomicInteger(0);
    protected final AtomicInteger takeNum = new AtomicInteger(0);

    public static void main(String[] args) {
        new PubTakeTest(10, 10, 100000).test();
    }

    public PubTakeTest(int capacity, int nPairs, int nTrials) {
        this.bb = new SemaphoreBoundedBuffer<>(capacity);
        this.nTrials = nTrials;
        this.nPairs = nPairs;
        this.barrier = new CyclicBarrier(nPairs * 2 + 1);
    }

    void test() {
        for (int i = 0; i < nPairs; i++) {
            pool.execute(new Producer());
            pool.execute(new Consumer());
        }
    }

    class Producer implements Runnable{

        @Override
        public void run() {
            try {
                int seed = (this.hashCode() ^ (int)System.nanoTime());
                int sum = 0;
                System.out.println("producer await1 执行前.....");
                barrier.await();
                System.out.println("producer await1 执行了.....");
                for (int i = nTrials; i > 0; --i) {
                    bb.put(seed);
                    sum += seed;
                    seed = xorShift(seed);
                }
                putSum.getAndAdd(sum);
                barrier.await();
                System.out.println("producer await2 执行了.....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    static int xorShift(int y) {
        y ^= (y << 6);
        y ^= (y >>> 21);
        y ^= (y << 7);
        return y;
    }

    class Consumer implements Runnable{

        @Override
        public void run() {
            try {
                System.out.println("consumer await1 执行前.....");
                barrier.await();
                System.out.println("consumer await1 执行了.....");
                int sum = 0;
                for (int i = nTrials; i > 0; --i) {
                    sum += bb.take();
                }
                takeNum.getAndAdd(sum);
                barrier.await();
                System.out.println("consumer await2 执行了.....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }
    }

}
