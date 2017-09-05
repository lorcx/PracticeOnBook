package java_mult_thread_core_technology.part7.group;

/**
 * Created by no_one on 2017/9/5.
 */
public class Run3 {
    public static void main(String[] args) {
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup group = new ThreadGroup(mainGroup, "A");
        Runnable runnable = () -> {
            System.out.println("RunMethod");
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread newThread = new Thread(group, runnable);
        newThread.setName("z");
        newThread.start();

        ThreadGroup[] listGroup = new ThreadGroup[Thread.currentThread().getThreadGroup().activeCount()];
        Thread.currentThread().getThreadGroup().enumerate(listGroup);
        System.out.println("main线程有多少个子线程" + listGroup.length + " 名字为：" + listGroup[0].getName());

        Thread[] listThread = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
        listGroup[0].enumerate(listThread);
        System.out.println(listThread[0].getName());

    }
}
