package java_concurrency_in_pratice.part7;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 计算素数
 * 取消操作：使用一个标志
 * <p>
 * prime 主要的
 *
 * @Author lx
 * @Date 2018/1/14 14:29
 */
public class PrimeGenerator implements Runnable {
    private static ExecutorService exec = Executors.newCachedThreadPool();
    private final List<BigInteger> primes = new ArrayList<>();
    private volatile boolean cancelled;// 取消状态

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancelled) {
            // 返回下一个素数
            p = p.nextProbablePrime();
            synchronized (this) {// 锁住当前对象
                primes.add(p);
            }
        }
    }

    public void cancel() {
        cancelled = true;
    }

    public synchronized List<BigInteger> get() {
        return new ArrayList<>(primes);
    }

    static List<BigInteger> aSecondOfPrimes() throws InterruptedException {
        PrimeGenerator generator = new PrimeGenerator();
        exec.execute(generator);
        try {
            Thread.sleep(1);
        } finally {
            // 防止睡眠失败取消失败
            generator.cancel();
        }
        return generator.get();
    }
}
