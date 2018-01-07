package java_concurrency_in_pratice.part4.object_composite;

import java_concurrency_in_pratice.anno.ThreadSafe;

import java.util.Vector;

/**
 * 扩展vector并添加一个“若没有则添加”方法
 * 这种同步方式是脆弱的，如果底层的类改变了同步策略并选择不同的锁来保护它的状态变量，那么子类会被破坏
 * absent 缺少
 * @Author lx
 * @Date 2018/1/7 14:18
 */
@ThreadSafe// 底层类不改变是安全
public class BetterVector<E> extends Vector<E> {
    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !contains(x);
        if (absent) {
            add(x);
        }
        return absent;
    }
}
