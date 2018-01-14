package java_concurrency_in_pratice.part7;

import java.util.concurrent.*;

/**
 * @Author lx
 * @Date 2018/1/14 16:16
 */
public class TimeRun {
    private static final ExecutorService exec = Executors.newCachedThreadPool();

    public static void timedRun(Runnable r, long timeout, TimeUnit unit) {
        Future<?> task = exec.submit(r);
        try {
            task.get(timeout, unit);
        } catch (InterruptedException e) {
            // 被中断
        } catch (ExecutionException e) {
            // 任务中抛出异常
        } catch (TimeoutException e) {
            // 超时任务将被取消
        } finally {
            // 任务已经完成
            task.cancel(true);
        }
    }
}
