package think_in_java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Pair不是线程安全的，别人交给你一个不是线程安全的类 ，可以创建pairManager来同步
 * 他的唯一一个public方法是sync的
 *
 * 其中increment方法是模版方法  模版设计模式
 * Created by dell on 2016/3/17.
 */
@SuppressWarnings("all")
public class Pair {
    private int x,y;

    public Pair() {
        this(0,0);
    }

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void incremntX() {
        x++;
    }

    public int getY() {
        return y;
    }

    public void incremntY() {
       y++;
    }


    @Override
    public String toString() {
        return "Pair{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public class PairValuesNoEqualException extends RuntimeException {
        public PairValuesNoEqualException() {
            super("pair values no null" + Pair.this);
        }
    }

    public void checkState(){
        if(x != y){
            throw new PairValuesNoEqualException();
        }
    }
}

@SuppressWarnings("all")
abstract class PairManager{
    AtomicInteger checkCount = new AtomicInteger();
    protected Pair p = new Pair();
    private List<Pair> storage = Collections.synchronizedList(new ArrayList<Pair>());

    public synchronized Pair getPair(){
        return new Pair(p.getX(),p.getY());
    }

    protected void store(Pair p){
        storage.add(p);
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {

        }
    }

    public abstract void increment();
}

@SuppressWarnings("all")
class PairManager1 extends PairManager{

    @Override
    public synchronized void increment() {
        p.incremntX();
        p.incremntY();
        store(getPair());
    }

}

@SuppressWarnings("all")
class PairManager2 extends PairManager{

    @Override
    public void increment() {
        Pair temp;
        synchronized (this){
            p.incremntX();
            p.incremntY();
            temp = getPair();
        }
        store(temp);
    }
}

@SuppressWarnings("all")
class PairManipulator implements Runnable {
    private PairManager pm;

    public PairManipulator(PairManager pm) {
        this.pm = pm;
    }

    @Override
    public void run() {
        while (true){
            pm.increment();
        }
    }

    @Override
    public String toString() {
        return "pair " + pm.getPair() + " checkCounter " + pm.checkCount.get();
    }
}

@SuppressWarnings("all")
class PairChecker implements Runnable {
    private PairManager pm;

    public PairChecker(PairManager pm) {
        this.pm = pm;
    }

    @Override
    public void run() {
        while (true) {
            pm.checkCount.incrementAndGet();
            pm.getPair().checkState();
        }
    }
}

@SuppressWarnings("all")
class CriticalSection{
    static void testApproaches(PairManager pman1,PairManager pman2){
        ExecutorService exec = Executors.newCachedThreadPool();
        PairManipulator p1 = new PairManipulator(pman1),
                        p2 = new PairManipulator(pman2);

        PairChecker pc1 = new PairChecker(pman1),
                    pc2 = new PairChecker(pman2);

        //2个线程加一  2个线程加一并获取值（原子性）
        exec.execute(p1);
        exec.execute(p2);
        exec.execute(pc1);
        exec.execute(pc2);

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("p1 " + p1 + "p2" + p2);
        System.exit(0);
    }

    public static void main(String[] args) {
        PairManager pman1 = new PairManager1(),
                    pman2 = new PairManager2();
        testApproaches(pman1,pman2);
    }
}



