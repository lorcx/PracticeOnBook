package java_mult_thread_core_technology.part3.join;

/**
 * Created by Administrator on 2017/8/28.
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        try {
            int secondVal = (int) (Math.random() * 10000);
            System.out.println(secondVal);
            Thread.sleep(secondVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Test {
    public static void main(String[] args) {
        try {
            MyThread m = new MyThread();
            m.start();
            m.join();// 当m 线程执行完毕后 才执行当前线程后续代码
            System.out.println("join 后");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
