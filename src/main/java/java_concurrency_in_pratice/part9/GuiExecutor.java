package java_concurrency_in_pratice.part9;

import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 基于SwingUtilities构建的Executor
 * @Author: lx
 * @Date: Created in 2018/2/7 0007
 */
public class GuiExecutor extends AbstractExecutorService {
    // 单利模式
    private static final GuiExecutor instance = new GuiExecutor();

    public GuiExecutor() {
    }

    public static GuiExecutor getInstance() {
        return instance;
    }



    @Override
    public void shutdown() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execute(Runnable r) {
        if (SwingUtilities.isEventDispatchThread()) {
            r.run();
        } else {
            try {
                SwingUtilities.invokeAndWait(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
