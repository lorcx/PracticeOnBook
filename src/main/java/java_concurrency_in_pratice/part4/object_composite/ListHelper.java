package java_concurrency_in_pratice.part4.object_composite;

import java_concurrency_in_pratice.anno.NoThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 没有使用同一个锁，操作并不是原子的
 * @Author lx
 * @Date 2018/1/7 14:24
 */
@NoThreadSafe
public class ListHelper<E> {
    // 使用的是内置锁
    public List<E> list = Collections.synchronizedList(new ArrayList<E>());

    // 使用的ListHelper锁
    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !list.contains(x);
        if (absent) {
            list.add(x);
        }
        return absent;
    }
}
