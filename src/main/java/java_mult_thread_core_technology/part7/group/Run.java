package java_mult_thread_core_technology.part7.group;

/**
 * 1级关联
 * Created by no_one on 2017/9/5.
 */
public class Run {
    public static void main(String[] args) {
        ThreadA ta = new ThreadA();
        ThreadB tb = new ThreadB();
        ThreadGroup tg = new ThreadGroup("lxxianchengzu");
        Thread ra = new Thread(tg, ta);
        Thread rb = new Thread(tg, ta);
        ra.start();
        rb.start();
        System.out.println("活动线程" + tg.activeCount());
        System.out.println("线程组名称" + tg.getName());
        System.out.println("main线程名称" + Thread.currentThread().getName());
    }
}

class ThreadA extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

class ThreadB extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

