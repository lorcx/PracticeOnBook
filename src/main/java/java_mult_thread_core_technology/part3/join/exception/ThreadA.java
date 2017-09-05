package java_mult_thread_core_technology.part3.join.exception;

/**
 * Created by Administrator on 2017/8/29.
 */
public class ThreadA extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String newStr = new String();
            Math.random();
        }
    }
}

class ThreadB extends Thread {
    @Override
    public void run() {
        try {
            ThreadA a = new ThreadA();
            a.start();
            a.join();
            System.out.println("线程b在run end");
        } catch (InterruptedException e) {
            System.out.println("线程b异常");
            e.printStackTrace();
        }
    }
}

class ThreadC extends Thread{
    private ThreadB threadB;

    public ThreadC(ThreadB threadB) {
        this.threadB = threadB;
    }

    @Override
    public void run() {
        threadB.interrupt();
    }
}

class RunJoinException {
    public static void main(String[] args) throws InterruptedException {
        ThreadB b = new ThreadB();
        b.start();
        Thread.sleep(500);
        ThreadC c = new ThreadC(b);
        c.start();
    }
}