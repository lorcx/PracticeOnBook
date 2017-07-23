package think_in_java;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子类AtomicInteger 在性能调优有用
 * 通常用它代替syn关键字
 * Created by lx on 2016/2/13.
 */
@SuppressWarnings("unused")
public class AtomicIntegerTest implements Runnable {

    private AtomicInteger i = new AtomicInteger(0);

    public int getValue(){
        return i.get();
    }

    /**
     * increment增量
     */
    private void evenIncrement(){
        i.addAndGet(2);
    }

    public void run() {
        while(true){
            evenIncrement();
        }

    }

    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            public void run() {
                System.out.println(1);
                System.err.print("aborting");  //aborting 异常终止
                System.exit(0);
            }
        },5000);

        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicIntegerTest ai = new AtomicIntegerTest();
        exec.execute(ai);
        while(true){
            int aval = ai.getValue();
            if(aval % 2 != 0){
                System.out.println(aval);
                System.exit(0);
            }
        }
    }
}
