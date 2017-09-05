package java_mult_thread_core_technology.part7.exception;

/**
 * Created by no_one on 2017/9/5.
 */
public class MyThread2 extends Thread{
    private String num;

    public MyThread2(ThreadGroup group, String name, String num) {
        super(group, name);
        this.num = num;
    }

    @Override
    public void run() {
        int numInt = Integer.parseInt(num);
        while (true) {
            System.out.println("死循环中：" + Thread.currentThread().getName());
        }
    }
}

class Run {
    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("我的线程组");
        MyThread2[] myThreads = new MyThread2[10];
        for (int i = 0; i < myThreads.length; i++) {
            myThreads[i] = new MyThread2(group, "线程" + (i + 1), "1");
            myThreads[i].start();
        }
        MyThread2 newT = new MyThread2(group, "报错线程", "a");
        newT.start();
    }
}