package java_mult_thread_core_technology.part3.wait_notify;

/**
 * Created by Administrator on 2017/8/28.
 */
public class MyRunnable {
    private static Object lock = new Object();

    private static Runnable runnablel = () -> {
        try {
            synchronized (lock) {
                System.out.println("wait begin timer=" + System.currentTimeMillis());
                // 5s 后自动解锁
                lock.wait(5000);
                System.out.println("wait end timer=" + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    private static Runnable runnable2 = () -> {
        synchronized (lock) {
            System.out.println("notify begin timer=" + System.currentTimeMillis());
            lock.notify();
            System.out.println("notify end timer=" + System.currentTimeMillis());
        }
    };

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(runnablel);
        t.start();
        Thread.sleep(3000);// 防止通知过早
        Thread t1 = new Thread(runnable2);
        t1.start();
    }
}
