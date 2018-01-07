package java_concurrency_in_pratice.part4;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @Author lx
 * @Date 2018/1/7 15:44
 */
public class HiddenIterator {
    private final Set<Integer> set = new HashSet<>();

    public synchronized void add(Integer i) {
        set.add(i);
    }

    public synchronized void remove(Integer i) {
        set.remove(i);
    }

    public void addThenThings() {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            add(r.nextInt());
        }
        System.out.println("set" + set);// 会隐式调用iterator，可能抛出异常00000000000000000000000000
    }
}
