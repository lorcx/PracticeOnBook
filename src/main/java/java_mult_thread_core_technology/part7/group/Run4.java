package java_mult_thread_core_technology.part7.group;

/**
 * Created by no_one on 2017/9/5.
 */
public class Run4 {
    public static void main(String[] args) {
        System.out.println("a线程" + Thread.currentThread().getName() + "所属线程组" + Thread.currentThread().getThreadGroup().getName()
        + "中的数量：" + Thread.currentThread().getThreadGroup().activeGroupCount());

        ThreadGroup group = new ThreadGroup("新线程");

        System.out.println("B线程" + Thread.currentThread().getName() + "所属线程组" + Thread.currentThread().getThreadGroup().getName()
                + "中的数量：" + Thread.currentThread().getThreadGroup().activeGroupCount());
        ThreadGroup[] threadGroups = new ThreadGroup[Thread.currentThread().getThreadGroup().activeGroupCount()];
        Thread.currentThread().getThreadGroup().enumerate(threadGroups, true);
        for (int i = 0; i < threadGroups.length; i++) {
            System.out.println("第一个线程组名称：" + threadGroups[i].getName());
        }

        /**
         * 获取根线程组
         */
        /*System.out.println("线程：" + Thread.currentThread().getName() + "所在线程组名：" + Thread.currentThread().getThreadGroup().getName());
        System.out.println("父线程组：" + Thread.currentThread().getThreadGroup().getParent().getName());*/
    }
}
