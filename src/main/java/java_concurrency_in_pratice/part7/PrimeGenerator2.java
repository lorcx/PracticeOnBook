package java_concurrency_in_pratice.part7;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 计算素数
 * 中断
 * <p>
 * prime 主要的
 *
 * @Author lx
 * @Date 2018/1/14 14:29
 */
public class PrimeGenerator2 implements Runnable {
    private final BlockingQueue<BigInteger> queue;

    public PrimeGenerator2(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        try {
            while (!Thread.currentThread().isInterrupted()) {
                // 返回下一个素数
                p = p.nextProbablePrime();
                queue.put(p);
            }
        } catch (InterruptedException e) {
            // 允许线程退出
        }
    }

    public void cancel() {
        Thread.currentThread().interrupt();
    }

}
