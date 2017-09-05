package java_mult_thread_core_technology.part3.thread_local.sub;

import java.util.Date;

/**
 * 子线程继承父线程变量
 * Created by Administrator on 2017/8/31.
 */
public class InheritableThreadLocalExt extends InheritableThreadLocal{
    @Override
    protected Object initialValue() {
        return new Date().getTime();
    }

    @Override
    protected Object childValue(Object parentValue) {
        return parentValue + "我是在子线程中加的";
    }
}

class Tools2 {
    public static InheritableThreadLocalExt tl = new InheritableThreadLocalExt();
}

class ThreadA extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("在ThreadA线程中取值=" + Tools2.tl.get());
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class RunInhertable {
    public static void main(String[] args) {
        try {
            for (int i = 0; i< 10; i++) {
                System.out.println("main线程中取值 =" + Tools2.tl.get());
                Thread.sleep(100);
            }
            Thread.sleep(5000);
            Thread a = new ThreadA();
            a.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}