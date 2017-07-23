package think_in_java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用executor管理线程
 * Created by lx on 2015/12/25.
 */
@SuppressWarnings("all")
public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 5;i++)
            service.execute(new LiftOff());
        service.shutdown();
    }

    /**
     * newCachedThreadPool()方法：
     * 创建一个可根据需要创建新线程的线程池，但是在以前构造的线程可用时将重用它们。
     * 对于执行很多短期异步任务的程序而言，这些线程池通常可提高程序性能。
     * 调用 execute 将重用以前构造的线程（如果线程可用）。
     * 如果现有线程没有可用的，则创建一个新线程并添加到池中。
     * 终止并从缓存中移除那些已有 60 秒钟未被使用的线程。因此，长时间保持空闲的线程池不会使用任何资源。
     */
}
