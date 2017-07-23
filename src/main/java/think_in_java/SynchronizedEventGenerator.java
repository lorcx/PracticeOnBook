package think_in_java;

/**
 * 使用Synchronized 同步
 * Created by lx on 2016/2/12.
 */
@SuppressWarnings("all")
public class SynchronizedEventGenerator extends IntGenerator{

    private int currentEvenVal = 0;

    /**
     * 有synchronized关键字每次都会 —— 加锁——  执行—— 解锁 而且从1开始 每次都加偶数 永远都不会失败
     *
     * @return
     */
    public synchronized int next() {
        ++currentEvenVal;
        System.out.println(Thread.currentThread().getName());
        Thread.yield();//线程挂起 执行其他线程 可以用于快速失败
        System.out.println(Thread.currentThread().getName());
        ++currentEvenVal;
        return currentEvenVal;
    }

    public static void main(String[] args) {
        EvenCheck.test(new SynchronizedEventGenerator());
    }
}
