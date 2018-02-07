package java_concurrency_in_pratice.part9;

import java.util.concurrent.*;

/**
 * 使用一个Executor实现SwingUtilties
 *
 * @Author: lx
 * @Date: Created in 2018/2/7 0007
 */
public class SwingUtilities {

    private static final ExecutorService exec = Executors.newSingleThreadExecutor(new SwingThreadFacftory());
    private static volatile Thread swingThread;

    private static class SwingThreadFacftory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            swingThread = new Thread(r);
            return swingThread;
        }
    }

    /**
     * 判断当前线程是否为swing事件线程
     *
     * @return
     */
    public static boolean isEventDispatchThread() {
        return Thread.currentThread() == swingThread;
    }

    /**
     * 将一个runnable对象放到swing事件线程中执行
     *
     * @param r
     */
    public static void invokeLater(Runnable r) {
        exec.execute(r);
    }

    /**
     * 执行并等待返回结果
     * @param r
     * @throws InterruptedException
     */
    public static void invokeAndWait(Runnable r) throws InterruptedException {
        Future f = exec.submit(r);
        try {
            f.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
