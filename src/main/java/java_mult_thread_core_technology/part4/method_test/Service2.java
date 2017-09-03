package java_mult_thread_core_technology.part4.method_test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * getQueueLength方法测试
 * 返回正在等待获取此锁的线程估计数
 * Created by lx on 2017/9/3.
 */
public class Service2 {
    public ReentrantLock lock = new ReentrantLock();
//    Condition c = lock.newCondition();

    public void serviceMethod() {
        try {
            lock.lock();
            System.out.println("ThreadName=" + Thread.currentThread().getName() + "进入方法");
            Thread.sleep(Integer.MAX_VALUE);
//            c.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

class Run2 {
    public static void main(String[] args) throws InterruptedException {
        final Service2 s = new Service2();
        Runnable r = () -> s.serviceMethod();

        Thread[] threadArray = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threadArray[i] = new Thread(r);
        }

        for (int i = 0; i < 10; i++) {
            threadArray[i].start();
        }
        Thread.sleep(2000);// 防止执行太快 早早的执行了main
        System.out.println("有" + s.lock.getQueueLength() + "个线程在等待获取锁！");

    }
}