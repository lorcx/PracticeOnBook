package think_in_java;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程本地存储
 * Created by lx on 2016/3/19.
 */
@SuppressWarnings("all")
public class Accessor implements Runnable {
    private final int id;

    public Accessor(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            ThreadLocalVariableHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }

    @Override
    public String toString() {
        return "#" + id + " : " + ThreadLocalVariableHolder.get();
    }
}

class ThreadLocalVariableHolder{
    private static ThreadLocal<Integer> value = new ThreadLocal<Integer>(){
        private Random rand = new Random(47);
        
        @Override
        protected synchronized Integer initialValue() {
            return rand.nextInt(10000);
        }
    };
    
    public static void increment(){
        value.set(value.get() + 1);
    }

    public static int get() {
        return value.get();
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0;i < 5;i++){
            exec.execute(new Accessor(i));
        }

        TimeUnit.SECONDS.sleep(3);//run for a while 运行一段时间  while一段时间  当...时候

//        System.out.println("main线程");

        exec.shutdownNow();//结束所有线程  shutdown()方法在终止前允许执行以前提交的任务  shutdownNow()方法阻止等待任务启动并试图停止当前正在执行的任务
    }
}