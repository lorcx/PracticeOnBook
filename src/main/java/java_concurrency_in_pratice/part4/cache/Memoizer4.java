package java_concurrency_in_pratice.part4.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 版本4：使用api中的原子复合操作代替
 *
 * 缓存的作用就是为了避免重复计算
 * @Author: lx
 * @Date: Created in 2018/1/8 0008
 */
public class Memoizer4<A, V> implements Computable<A, V>  {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memoizer4(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V computable(A arg) throws InterruptedException {
        Future<V> task = cache.get(arg);
        if (task == null) {
            FutureTask ft = new FutureTask(() -> c.computable(arg));
            task = cache.putIfAbsent(arg, ft);
            if (task == null) {// null 等于第一次插入，返回上一次的结果。如果返回不是null表示没有put
                task = ft;
                ft.run();
            }
        }
        try {
            return task.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

//    public V computable2(A arg) throws InterruptedException {
//        Future<V> task = null;
//        FutureTask ft = null;
//        if (task == null) {
//            ft = new FutureTask(() -> c.computable(arg));
//        }
//        task = cache.putIfAbsent(arg, ft);
//        if (task == null) {// null 等于第一次插入，返回上一次的结果。如果返回不是null表示没有put
//            task = ft;
//            ft.run();
//        }
//        try {
//            return task.get();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
