package java_concurrency_in_pratice.part4.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 版本3：使用futuretask,问题是“先检查后执行”是一个复合操作，不是原子操作，会导致执行相同的结果。
 *
 * 缓存的作用就是为了避免重复计算
 * @Author: lx
 * @Date: Created in 2018/1/8 0008
 */
public class Memoizer3<A, V> implements Computable<A, V>  {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memoizer3(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V computable(A arg) throws InterruptedException {
        Future<V> task = cache.get(arg);
        if (task == null) {
            FutureTask ft = new FutureTask(() -> c.computable(arg));
            task = ft;
            cache.put(arg, ft);
            ft.run();
        }
        try {
            return task.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
