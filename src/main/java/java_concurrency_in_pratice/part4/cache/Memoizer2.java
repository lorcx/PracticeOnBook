package java_concurrency_in_pratice.part4.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 版本2：虽然用ConcurrentHashMap替代了synchonized在并发性能上有了很大的提升，
 * 但是存在的问题是有可能多个线程会同时计算一个相同的值。线程a执行f(27),而线程b不知道线程a正在执行中所以也执行了。
 *
 * 缓存的作用就是为了避免重复计算
 * @Author: lx
 * @Date: Created in 2018/1/8 0008
 */
public class Memoizer2<A, V> implements Computable<A, V>  {
    private final Map<A, V> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memoizer2(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V computable(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.computable(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
