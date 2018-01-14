package java_concurrency_in_pratice.part7;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author lx
 * @Date 2018/1/14 15:44
 */
public class TimedRun2 {
    private static final ScheduledExecutorService cancelExec = new ScheduledThreadPoolExecutor(1);
    
    public static void timedRun(final Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
        class RethrowableTask implements Runnable {
            private volatile Throwable t;

            @Override
            public void run() {
                try {
                    r.run();
                } catch (Throwable e) {
                    this.t = t;
                }
            }
            
            void reThrow() {
                if (t != null) {
                    throw launderThrowable(t);
                }
            }

            private RuntimeException launderThrowable(Throwable t) {
                return null;
            }
        }

        RethrowableTask task = new RethrowableTask();
        final Thread taskThread = new Thread(task);
        taskThread.start();
        cancelExec.schedule(new Runnable() {
            @Override
            public void run() {
                taskThread.interrupt();
            }
        }, timeout, unit);

        taskThread.join(unit.toMillis(timeout));
        task.reThrow();
    }
}
