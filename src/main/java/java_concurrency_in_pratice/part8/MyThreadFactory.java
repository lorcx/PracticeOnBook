package java_concurrency_in_pratice.part8;

import java.util.concurrent.ThreadFactory;

/**
 * 自定义线程工厂
 * @Author lx
 * @Date 2018/1/28 15:29
 */
public class MyThreadFactory implements ThreadFactory {
    private final String poolName;

    public MyThreadFactory(String poolName) {
        this.poolName = poolName;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new MyThread(r, poolName);
    }
}

class MyThread extends Thread {
    public MyThread(Runnable target, String name) {
        super(target, name);
    }

    @Override
    public void run() {
        super.run();
    }
}