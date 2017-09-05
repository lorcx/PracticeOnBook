package java_mult_thread_core_technology.part3.thread_local;

/**
 * Created by Administrator on 2017/8/31.
 */
public class ThreadLoacl1 {
    public static ThreadLocal t1 = new ThreadLocal();

    public static void main(String[] args) {
        if (null == t1.get()) {
            System.out.println("无值");
            t1.set("这是值");
        }

        System.out.println(t1.get());
    }
}
