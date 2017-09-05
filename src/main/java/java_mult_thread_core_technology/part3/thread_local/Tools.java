package java_mult_thread_core_technology.part3.thread_local;


import java_mult_thread_core_technology.part3.thread_local.extend.ThreadLocalExt;

/**
 * 验证线程间调用ThradLocal的隔离性
 * Created by Administrator on 2017/8/31.
 */
public class Tools {
    public static ThreadLocalExt tl = new ThreadLocalExt();
}

class ThreadA extends Thread{
    @Override
    public void run() {
        try {
            for (int i = 0 ; i < 10; i++) {
                System.out.println("线程a取值=" + Tools.tl.get());
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class RunExt {
    public static void main(String[] args) {
        try {
            for (int i = 0 ; i < 10; i++) {
                System.out.println("线程main取值=" + Tools.tl.get());
                Thread.sleep(100);
            }
            Thread.sleep(5000);
            ThreadA a = new ThreadA();
            a.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
