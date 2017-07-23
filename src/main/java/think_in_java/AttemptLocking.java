package think_in_java;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 显示lock锁
 * Created by dell on 2016/3/16.
 */
@SuppressWarnings("all")
public class AttemptLocking {
    private ReentrantLock lock = new ReentrantLock();

    public void untimed(){
        boolean captured = lock.tryLock();//captured 被捕获的
        try {
            System.out.println("untimed try lock()" + captured);
        } finally {
            if(captured){
                lock.unlock();
            }
        }
    }

    public void timed(){
        boolean captured = false;
        try {
            captured = lock.tryLock(2, TimeUnit.SECONDS);//2s
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            System.out.println("timed try lock()" + captured);
        } finally {
            if(captured){
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) {
        final AttemptLocking locking = new AttemptLocking();
        locking.untimed();
        locking.timed();
        new Thread(){
            {
                setDaemon(true);
            }
            public void run() {
                locking.lock.lock();
                System.out.println("111111");
            }
        }.start();

        Thread.yield(); //给这两个任务一个机会
        locking.untimed();
        locking.timed();
    }
}
