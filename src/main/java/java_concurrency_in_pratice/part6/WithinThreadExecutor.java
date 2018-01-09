package java_concurrency_in_pratice.part6;

import java.util.concurrent.Executor;

/**
 * 串行执行任务
 * @Author: lx
 * @Date: Created in 2018/1/9 0009
 */
public class WithinThreadExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
