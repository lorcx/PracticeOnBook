package java_concurrency_in_pratice.part15;

import java.util.concurrent.atomic.AtomicReference;

/**
 * CAS 无阻塞并发栈
 * @Author lx
 * @Date 2018/2/13 15:48
 */
public class ConcurrentStack<E> {
    AtomicReference<Node<E>> top = new AtomicReference<>();

    public void push(E item) {
        Node<E> newHead = new Node<>(item);
        Node<E> oldHead;
        do {
            oldHead = top.get();
            newHead.next = oldHead;
        } while (!top.compareAndSet(oldHead, newHead));
    }


    private static class Node<E> {
        public final E item;
        public Node<E> next;

        public Node(E item) {
            this.item = item;
        }
    }
}
