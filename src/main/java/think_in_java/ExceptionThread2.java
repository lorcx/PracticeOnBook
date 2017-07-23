package think_in_java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 线程异常
 * 自定义的捕获器
 * Created by dell on 2016/2/3.
 */
public class ExceptionThread2 implements Runnable {

    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("run() by " + t);
        System.out.println("eh = " + t.getUncaughtExceptionHandler());
        throw new RuntimeException();
    }
}

@SuppressWarnings("all")
class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{

    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught " + e);
    }
}

@SuppressWarnings("all")
class HandlerThreadFactory implements ThreadFactory {

    public Thread newThread(Runnable r) {
        System.out.println(this + "createing new Thread");
        Thread t = new Thread(r);
        System.out.println("created " + t);
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        System.out.println("eh=" + t.getUncaughtExceptionHandler());
        return t;
    }
}

@SuppressWarnings("all")
class CaptureUncaughtException{
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());//创建一个可根据需要创建新线程的线程池
        exec.execute(new ExceptionThread2());
    }
}
