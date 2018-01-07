package java_concurrency_in_pratice.part4.object_composite;

import java_concurrency_in_pratice.anno.ThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 使用了同一个锁
 * @Author lx
 * @Date 2018/1/7 14:28
 */
@ThreadSafe
public class ListHelper1<E> {
    // 使用的是内置锁
    public List<E> list = Collections.synchronizedList(new ArrayList<E>());

    // 使用的ListHelper锁
    public boolean putIfAbsent(E x) {
        synchronized (list) {
            boolean absent = !list.contains(x);
            if (absent) {
                list.add(x);
            }
            return absent;
        }
    }
}
