package java_mult_thread_core_technology.part2.sync;

/**
 * Created by Administrator on 2017/8/18.
 */
public class Task {
    public void doLongTimeTask() {
        for (int i = 0; i < 100; i++) {
            System.out.println("nosynchronzied threadName = " + Thread.currentThread().getName() + " i = " + (i + 1));
        }
        System.out.println("");
        synchronized (this) {
            for (int i = 0; i < 100; i++) {
                System.out.println("synchronzied threadName = " + Thread.currentThread().getName() + " i = " + (i + 1));
            }
        }
    }
}

class Thread1 extends Thread{
    private Task task;

    public Thread1(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        task.doLongTimeTask();
    }
}

class Thread2 extends Thread{
    private Task task;

    public Thread2(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        task.doLongTimeTask();
    }
}

class Run8 {
    public static void main(String[] args) {
        Task t = new Task();
        Thread ta = new Thread1(t);
        Thread tb = new Thread2(t);

        ta.start();
        tb.start();
    }
}