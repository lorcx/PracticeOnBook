package java_mult_thread_core_technology.part2.atomic;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Administrator on 2017/8/23.
 */
public class MyService3 {
    public static AtomicLong aiRef = new AtomicLong();

    public synchronized void addSum() {
        System.out.println(Thread.currentThread().getName() + "加了100以后的值：" + aiRef.addAndGet(100));
        aiRef.addAndGet(1);
    }
}

class MyThread extends Thread{
    private MyService3 service3;

    public MyThread(MyService3 service3) {
        this.service3 = service3;
    }

    @Override
    public void run() {
        service3.addSum();
    }
}

class Run22 {
    public static void main(String[] args) throws InterruptedException {
        MyService3 ms3 = new MyService3();
        MyThread[] arr = new MyThread[5];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new MyThread(ms3);
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i].start();
        }
        Thread.sleep(1000);
        System.out.println(MyService3.aiRef.get());
    }
}