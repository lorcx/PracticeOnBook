package java_concurrency_actual;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/5/4 0004.
 */
public class CountDownLatchDemo implements Runnable {
    // 倒计时10个线程任务
    static final CountDownLatch end = new CountDownLatch(10);
    static final CountDownLatchDemo demo = new CountDownLatchDemo();

    @Override
    public void run() {
        try {
            // 模拟检查任务
            Thread.sleep(new Random().nextInt(10) * 1000);
            System.out.println("检查任务完成。");
            end.countDown(); // 表示一个线程已经执行完任务了
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            exec.submit(demo);
        }

        // 等待所有线程执行完成，等待检查
        end.await();
        System.out.println("fire");
        exec.shutdown();
    }

}
