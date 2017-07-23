package think_in_java;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *
 * 线程中断
 * executors类中的shutdown()方法是给每个启动的线程发送一个interrupt中断
 * 如果要中断某一个线程 就不能调用execute启动线程
 * 要调用submit启动 返回fcture接口
 * Created by dell on 2016/3/22.
 */
@SuppressWarnings("all")
public class SleepBlocked implements Runnable {
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(100);//100分
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        }
        System.out.println("exit SleepBlocked().run() ");
    }
}

@SuppressWarnings("all")
class IoBlocked implements Runnable {
    private InputStream in;

    public IoBlocked(InputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            System.out.println("wait to read()");
            in.read();
        } catch (IOException e) {
            if(Thread.currentThread().isInterrupted()){
                System.out.println("interrupt from blocked I/O");
            }else{
                throw new RuntimeException(e);
            }
        }
        System.out.println("exit IoBlocked().run() ");
    }
}

@SuppressWarnings("all")
class SynhronizedBlocked implements Runnable {
    @Override
    public void run() {
        System.out.println("try call f()");
        f();
        System.out.println("exit SynchronizedBlocked().run() ");
    }

    public SynhronizedBlocked() {
        new Thread(){
            @Override
            public void run() {
                f();//这个线程锁了
            }
        }.start();//千万不能写run 否则结束不了
    }

    public synchronized void f(){
        while(true){//不释放锁 让出资源 让别人持有锁
            Thread.yield();
        }
    }
}

@SuppressWarnings("all")
class Interrupteding{
    private static ExecutorService exec = Executors.newCachedThreadPool();

    static void test(Runnable rn) throws InterruptedException {
        Future<?> f = exec.submit(rn);//返回这个线程的上下文
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("interrupted :" + rn.getClass().getName());
        f.cancel(true);//中断任务
        System.out.println("interrupted send to :" + rn.getClass().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        test(new SleepBlocked());  //可中断
        test(new IoBlocked(System.in));//不可中断
        test(new SynhronizedBlocked());//不可中断
        //只能中断抛出interrupt异常的情况，不能终端io中和获得synchronization锁的情况
        TimeUnit.SECONDS.sleep(3);
//        TimeUnit.MILLISECONDS.sleep(10000);
        System.out.println("aborting exit ");
        System.exit(0);
    }

}
