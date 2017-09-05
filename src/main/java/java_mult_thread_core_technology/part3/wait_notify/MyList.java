package java_mult_thread_core_technology.part3.wait_notify;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/23.
 */
public class MyList {
    private volatile List list = new ArrayList<>();

    public void add() {
        list.add("ss");
    }

    public int size() {
        return list.size();
    }
}

class ThreadA extends Thread{
    private MyList list;

    public ThreadA(MyList list) {
        this.list = list;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                list.add();
                System.out.println("第" + (i + 1) + "个元素");
                Thread.sleep(1000);
            }
            System.out.println("线程A执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadB extends Thread{
    private MyList list;

    public ThreadB(MyList list) {
        this.list = list;
    }

    @Override
    public void run() {
        try {
            while (true) {
//                System.out.println("当前个数;" + list.size());
                if (list.size() == 5) {
                    System.out.println("当前个数5，我要退出");
                    throw new InterruptedException();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Run23 {
    public static void main(String[] args) {
        MyList myList = new MyList();
        Thread ta = new ThreadA(myList);
        ta.start();
        Thread tb = new ThreadB(myList);
        tb.start();
    }
}



