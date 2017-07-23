package think_in_java;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lx on 2016/3/19.
 */
@SuppressWarnings("all")
public class ExplicitPairManager1 extends PairManager{
    private Lock lock = new ReentrantLock();

    @Override
    public void increment() {
        lock.lock();
        try {
            p.incremntX();
            p.incremntY();
            store(getPair());
        } finally {
            lock.unlock();
        }
    }
}

@SuppressWarnings("all")
class ExplicitPairManager2 extends PairManager{
    private Lock lock = new ReentrantLock();

    @Override
    public void increment() {
        Pair temp;
        lock.lock();
        try {
            p.incremntX();
            p.incremntY();
            temp = getPair();
        } finally {
            lock.unlock();
        }
        store(temp);
    }
}

@SuppressWarnings("all")
class ExplicitCriticalSection{
    public static void main(String[] args) {
        PairManager p1 = new ExplicitPairManager1(),
                    p2 = new ExplicitPairManager2();
        CriticalSection.testApproaches(p1,p2);
    }
}