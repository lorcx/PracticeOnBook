package java_mult_thread_core_technology.part7.group;


/**
 * Created by no_one on 2017/9/5.
 */
public class MyThread extends Thread {
    public MyThread(ThreadGroup group , String name) {
        super(group, name);
    }

    @Override
    public void run() {
        System.out.println("ThreadName=" + Thread.currentThread().getName() + "read for");

        while (!this.isInterrupted());

        System.out.println("ThreadName=" + Thread.currentThread().getName() + "end");
    }
}

class RUn {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group = new ThreadGroup("我的线程组");
        for (int i = 0; i < 5; i++) {
            MyThread thread = new MyThread(group, "线程 " + (i + 1));
            thread.start();
        }
        Thread.sleep(5000);
        group.interrupt();
        System.out.println("刁难");
    }
}