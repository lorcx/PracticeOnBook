package java_mult_thread_core_technology.part3.wait_notify;

import java.util.ArrayList;
import java.util.List;

public class MyList2 {
    private static List list = new ArrayList<>();

    public static void add() {
        list.add("ss");
    }

    public static int size() {
        return list.size();
    }
}


class ThreadA2 extends Thread{
    private Object lock;

    public ThreadA2(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
             synchronized (lock) {
                if (MyList2.size() != 5) {
                    System.out.println("wait begin " + System.currentTimeMillis());
                    lock.wait();
                    System.out.println("wait end " + System.currentTimeMillis());
                }
             }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadB2 extends Thread{
    private Object lock;

    public ThreadB2(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    MyList2.add();
                    if (MyList2.size() == 5) {
                        lock.notify();
                        System.out.println("已发出通知");
                    }
                    System.out.println("添加第" + (i + 1) + "个元素");
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Run25 {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        Thread ta = new ThreadA2(o);
        ta.start();
        Thread.sleep(50);
        Thread tb = new ThreadB2(o);
        tb.start();
    }
}



