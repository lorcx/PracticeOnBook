package think_in_java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 优先级
 * Created by lx on 2015/12/25.
 */
@SuppressWarnings("all")
public class SimplePriorities implements Runnable {

    private int countDown = 5;
    private volatile double d;
    private int priority;//优先级

    public SimplePriorities(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {  //Thread.currentThread()返回对当前正在执行的线程对象的引用。
        return Thread.currentThread() + " :" +
                "countDown=" + countDown;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(priority);//设置优先级
        while(true){
            for (int i = 0;i < 100000;i++){
                d += (Math.PI + Math.E) / (double)i;
                if(i % 1000 ==0){
                    Thread.yield();
                }
            }
            System.out.println(this);
            if(--countDown == 0)
                return;
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();//创建线程池
        for(int i = 0;i < 5;i++){
            service.execute(new SimplePriorities(Thread.MIN_PRIORITY));
        }
        service.execute(new SimplePriorities(Thread.MAX_PRIORITY));
        service.shutdown();
        /**
         * Thread[pool-1-thread-6,10,main]
         * pool-1-thread-6线程名
         * 10优先级
         * main线程组
         *
         */
    }
}
