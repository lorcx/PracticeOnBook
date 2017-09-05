package java_mult_thread_core_technology.part3.exception;

/**
 * Created by Administrator on 2017/8/28.
 */
public class WaitInterrupt {
    public void testMethod(Object lock) {
        synchronized (lock) {
            try {
                System.out.println("begin wait()");
                lock.wait();
                System.out.println("end wait()");
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("wait 中断");
            }
        }
    }
}

class ThreadA extends Thread {
    private Object lock;

    public ThreadA(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        WaitInterrupt wi = new WaitInterrupt();
        wi.testMethod(lock);
    }
}

class Run31 {
    public static void main(String[] args) {
        try {
            Object lock = new Object();
            Thread ta = new ThreadA(lock);
            ta.start();
            Thread.sleep(5000);
            ta.interrupt();// 线程中断会释放锁，
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
