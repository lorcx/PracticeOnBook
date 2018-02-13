package java_concurrency_in_pratice.part15;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 非阻塞算法 1996
 * put要更新2个指针，一个是最后一个元素的next指向新的元素，另一个是tail指向最后一个
 * 如果某个线程只完成了部分操作，另一个线程可以帮助他完成
 * @Author lx
 * @Date 2018/2/13 15:58
 */
public class LinkedQueue<E> {
    private static class Node<E> {
        final E item;
        final AtomicReference<LinkedQueue.Node<E>> next;

        public Node(E item, LinkedQueue.Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<>(next);
        }
    }

    // 虚节点
    private final LinkedQueue.Node<E> dummy = new LinkedQueue.Node<>(null, null);
    private final AtomicReference<LinkedQueue.Node<E>> head = new AtomicReference<>(dummy);
    private final AtomicReference<LinkedQueue.Node<E>> tail = new AtomicReference<>(dummy);

    public boolean put(E item) {
        LinkedQueue.Node<E> newNode = new LinkedQueue.Node<>(item, null);
        while (true) {
            LinkedQueue.Node<E> curTail = tail.get();
            LinkedQueue.Node<E> tailNext = curTail.next.get();
            if (curTail == tail.get()) {
                if (tailNext != null) {
                    // 队列处于中间节点，推进尾节点
                    tail.compareAndSet(curTail, tailNext);
                } else {
                    // 处于稳定状态，插入新节点
                    if (curTail.next.compareAndSet(null, newNode)) {
                        // 插入操作成功，尝试推进尾节点
                        tail.compareAndSet(curTail, newNode);
                        return true;
                    }
                }
            }
        }
    }

}
