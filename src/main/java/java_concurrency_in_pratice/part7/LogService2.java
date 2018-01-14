package java_concurrency_in_pratice.part7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author lx
 * @Date 2018/1/14 18:19
 */
public class LogService2 {
    private static final long TIMEOUT = 1;
    private static TimeUnit UNIT;
    private final ExecutorService exec = Executors.newSingleThreadExecutor();

    public void start() {
    }

    public void stop() {
        try {
            exec.shutdown();
            exec.awaitTermination(TIMEOUT, UNIT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //xxxxx
        }
    }
}
