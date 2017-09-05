package java_mult_thread_core_technology.part3.wait_notify;

/**
 * Created by Administrator on 2017/8/23.
 */
public class MyThread1 extends Thread{
    private Object lock;

    public MyThread1(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                System.out.println("开始 wait time " + System.currentTimeMillis());
                lock.wait();
                System.out.println("结束 wait time " + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread2 extends Thread{
    private Object lock;

    public MyThread2(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("开始 wait time " + System.currentTimeMillis());
            lock.notify();
            System.out.println("结束 wait time " + System.currentTimeMillis());
        }
    }
}

class Run24 {
        public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        MyThread1 mt1 = new MyThread1(lock);
        mt1.start();
        Thread.sleep(3000);
        MyThread2 mt2 = new MyThread2(lock);
        mt2.start();
    }
}
