package java_mult_thread_core_technology.part3.join;

/**
 * Created by Administrator on 2017/8/31.
 */
public class ThreadA extends Thread{
    private ThreadB b;

    public ThreadA(ThreadB b) {
        this.b = b;
    }

    @Override
    public void run() {
        try {
            synchronized (b) {
                System.out.println("begin A ThreadName=" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
                Thread.sleep(5000);
                System.out.println("end A ThreadName=" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadB extends Thread{
    @Override
    public synchronized void run() {
        try {
            System.out.println("begin B ThreadName=" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("end B ThreadName=" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Run33 {
    public static void main(String[] args) {
        try {
            ThreadB b = new ThreadB();
            ThreadA a = new ThreadA(b);
            a.start();
            b.start();
            b.join(2000);
            System.out.println("main end " + System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}