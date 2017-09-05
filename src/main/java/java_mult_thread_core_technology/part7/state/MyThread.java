package java_mult_thread_core_technology.part7.state;

/**
 * Created by no_one on 2017/9/4.
 */
public class MyThread extends Thread{
    public MyThread() {
        System.out.println("构造方法中(main)" + Thread.currentThread().getState());
    }

    @Override
    public void run() {
        System.out.println("run方法中" + Thread.currentThread().getState());
       /* try {
            this.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}

class Run {
    public static void main(String[] args) throws InterruptedException {
        MyThread t = new MyThread();
        System.out.println("main方法的状态1：" + t.getState());
        Thread.sleep(1000);
        t.start();
        Thread.sleep(1000);
        System.out.println("main方法的状态2：" + t.getState());
    }
}