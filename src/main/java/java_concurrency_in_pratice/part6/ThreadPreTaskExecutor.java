package java_concurrency_in_pratice.part6;

import java.util.concurrent.Executor;

/**
 * 自定义executor 为每个请求创建一个线程
 * @Author: lx
 * @Date: Created in 2018/1/9 0009
 */
public class ThreadPreTaskExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        new Thread(command).run();
    }
}
