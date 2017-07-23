package think_in_java;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * mutex互斥
 * 使用显示lock对象
 *
 *
 * synchronized 和 lock 区别:
 *   1.synchronized别lock的代码少
 *   2.synchronized如果出错只能抛异常，并不能处理 ，而lock能处理
 *
 *   一般lock用于特殊的用途
 *
 * Created by lx on 2016/2/12.
 */
@SuppressWarnings("all")
public class MutexEvenGenerator extends IntGenerator{
    private int currentEvenVal = 0;
    private Lock lock = new ReentrantLock();//reentrant 凹角；再进入

    /**
     * 显示的加锁
     *
     * @return
     */
    public int next() {
        lock.lock();
        try{
            ++currentEvenVal;
//            System.out.println(Thread.currentThread().getName());
            Thread.yield();//线程挂起 执行其他线程 可以用于快速失败
//            System.out.println(Thread.currentThread().getName());
            ++currentEvenVal;
            return currentEvenVal;// 一定要在解锁前返回，不然就将资源暴露给别的任务了
        }finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        EvenCheck.test(new MutexEvenGenerator());
    }
}
