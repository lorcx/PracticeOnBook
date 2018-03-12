package java_concurrency_in_pratice;

import java.util.concurrent.Semaphore;

/**
 * @Author lx
 * @Date 2018/1/28 16:47
 */
public class T {
    public static void main(String[] args) throws InterruptedException {
//        ExecutorService exec = Executors.newCachedThreadPool();
//        exec.execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(10000);
//                    System.out.println("睡完");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        System.out.println("main 执行");
//
//        exec.shutdown();
//        exec.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
//        System.out.println("end");
        Semaphore sem = new Semaphore(-1);
        System.out.println(sem.availablePermits());
        sem.release();
        System.out.println(sem.availablePermits());
        sem.acquire();
//        sem.release();
//        System.out.println(sem.availablePermits());
//        sem.acquire();
//        System.out.println(sem.availablePermits());
    }
}
