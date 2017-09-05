package java_mult_thread_core_technology.part3.thread_local.extend;

import java.util.Date;

/**
 * ThreadLocal扩展
 * Created by Administrator on 2017/8/31.
 */
public class ThreadLocalExt extends ThreadLocal{
    @Override
    protected Object initialValue() {
        return new Date().toString();
    }
}

class ExtRun {
    public static ThreadLocalExt tle = new ThreadLocalExt();

    public static void main(String[] args) {
        if (tle.get() != null) {
            System.out.println("1");
        }
        System.out.println(tle.get());
        System.out.println(tle.get());
        System.out.println(tle.get());
    }
}