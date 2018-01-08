package java_concurrency_in_pratice.part4;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 用futuretask来提前加载稍后需要的数据
 * @Author: lx
 * @Date: Created in 2018/1/8 0008
 */
public class Preloader {
    // 高负载任务
    ProductInfo loadProductInfo() throws DataLoadException {
        return null;
    }

    // FutureTask 是异步执行任务
private final FutureTask<ProductInfo> future = new FutureTask<>(new Callable<ProductInfo>() {
        @Override
        public ProductInfo call() throws Exception {
            return loadProductInfo();
        }
    });

    private final Thread thread = new Thread(future);

    public void start() {
        thread.start();
    }

    public ProductInfo get() throws InterruptedException, DataLoadException {
        try {
            // get方法在没有返回结果前一直阻塞
            return future.get();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof DataLoadException) {
                throw (DataLoadException)cause;
            } else {
                throw launderThrowable(cause);
            }
        }
    }

    private static RuntimeException launderThrowable(Throwable t) {
        if (t instanceof RuntimeException) {
            return (RuntimeException) t;
        } else if (t instanceof Error) {
            throw (Error)t;
        } else {
            throw new IllegalStateException("not unchecked", t);
        }
    }
}

interface ProductInfo {

}

class DataLoadException extends Exception {

}