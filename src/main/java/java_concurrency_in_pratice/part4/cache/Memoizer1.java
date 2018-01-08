package java_concurrency_in_pratice.part4.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 第一版使用了synchronized 降低了并发访问，多个线程执行顺序是串行。一个明显的伸缩性问题：可能被阻塞时间比较长
 * @Author: lx
 * @Date: Created in 2018/1/8 0008
 */
public class Memoizer1<A, V> implements Computable<A, V>{
    private final Map<A, V> cache = new HashMap<>();
    private final Computable<A, V> c;

    public Memoizer1(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public synchronized V computable(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.computable(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
