package think_in_java;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lx on 2016/2/13.
 */
@SuppressWarnings("unused")
public class AtomicIntegerGenerator extends IntGenerator{
    private AtomicInteger currentVal = new AtomicInteger(0);

    public int next() {
        return currentVal.addAndGet(2);
    }

    public static void main(String[] args) {
        EvenCheck.test(new AtomicIntegerGenerator());//以原子方式将给定值与当前值相加。
    }

}
