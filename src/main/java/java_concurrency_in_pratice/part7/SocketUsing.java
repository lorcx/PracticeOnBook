package java_concurrency_in_pratice.part7;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * 通过newTaskFor将非标准的的取消操作封装在一个任务中
 *
 * @Author lx
 * @Date 2018/1/14 16:58
 */
public abstract class SocketUsing<T> implements CancellabelTask<T> {
    private Socket socket;

    protected synchronized void setSocket(Socket s) {
        socket = s;
    }

    public synchronized void cancel() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public RunnableFuture<T> newTask() {
        return new FutureTask<T>(this) {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                try {
                    SocketUsing.this.cancel();
                } finally {
                    return super.cancel(mayInterruptIfRunning);
                }
            }
        };
    }
}

interface CancellabelTask<T> extends Callable<T> {
    void cancel();

    RunnableFuture<T> newTask();
}

class CancellingExecutor extends ThreadPoolExecutor {

    public CancellingExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        if (callable instanceof CancellabelTask) {
            return ((CancellabelTask) callable).newTask();
        } else {
            return super.newTaskFor(callable);
        }
    }
}