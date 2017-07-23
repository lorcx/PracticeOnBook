package think_in_java;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dell on 2016/3/23.
 */
@SuppressWarnings("all")
public class BlockMutex {
    private Lock lock = new ReentrantLock();

    public BlockMutex() {
        lock.lock();
    }

    public void f(){
        try {
            System.out.println(111);
            lock.lockInterruptibly();
            System.out.println("f()");
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        }
    }
}

@SuppressWarnings("all")
class Blocked2 implements Runnable {
    BlockMutex bm = new BlockMutex();
    @Override
    public void run() {
        System.out.println("wating for f() in blockmutex");
        bm.f();//互斥
        System.out.println("broken out of blocked ");
    }
}

@SuppressWarnings("all")
class Interrupting2{
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Blocked2());
        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("issuing t.interrupt");
        t.interrupt();
    }
}
