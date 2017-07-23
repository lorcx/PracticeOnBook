package think_in_java;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * countDownLatch使用
 * 用来同步一个或多个任务,
 * 强制它们等待有其他任务执行的一组操作完成
 * Created by lx on 2016/3/27.
 */
public class TaskPortion implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private static Random rand = new Random(47);
    private final CountDownLatch latch;

    public TaskPortion(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            doWork();
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void doWork() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
        System.out.println(this + "completed");
    }

    @Override
    public String toString() {
        return String.format("%1$-3d", id);
    }
}

class WaitTask implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final CountDownLatch latch;

    public WaitTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();
            System.out.println("latch barrier passed for " + this);//barrier障碍物
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return String.format("WaitTask %1$-3d", id);
    }
}

class CountDownLatchDemo{
    static final int SIZE = 100;

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(SIZE);
        for(int i = 0;i < 10;i++){
            exec.execute(new WaitTask(latch));
        }
        for(int i = 0;i < SIZE;i++){
            exec.execute(new TaskPortion(latch));
        }
        System.out.println("latch all tasks");
        exec.shutdown();//当所有任务都完成时 退出
    }
}