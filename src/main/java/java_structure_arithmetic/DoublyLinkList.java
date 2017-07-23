package java_structure_arithmetic;

/**
 * 双向链表
 * Created by dell on 2016/8/25.
 */
public class DoublyLinkList {
    private LinkD first;
    private LinkD last;

    public DoublyLinkList() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    /**
     * 在开头插入
     *
     * @param iData
     */
    public void insertFirst(int iData) {
        LinkD newLink = new LinkD(iData);
        if (isEmpty()) {
            last = newLink;
        } else {
            first.prev = newLink;
        }
        newLink.next = first;
        first = newLink;
    }

    /**
     * 插入结尾
     */
    public void insertLast(int iData) {
        LinkD newLink = new LinkD(iData);
        if (isEmpty()) {
            first = newLink;
        } else {
            last.next = newLink;
            newLink.prev = last;
        }
        last = newLink;
    }

    /**
     * 删除第一个
     *
     * @return
     */
    public LinkD deleteFirst() {
        LinkD temp = first;
        if (first.next == null) {
            last = null;// 删除了最后一个
        } else {
            first.next.prev = null;
        }
        first = first.next;
        return temp;
    }

    /**
     * 删除最后一个
     *
     * @return
     */
    public LinkD deleteLast() {
        LinkD temp = last;
        if (first.next == null) {
            first = null;//删除了最后一个
        } else {
            last.prev.next = null;
        }
        last = last.prev;
        return temp;
    }

    /**
     * 插入到某项之后
     *
     * @return
     */
    public boolean insertAfter(int key, int iData) {
        LinkD current = first;
        while (current.iData != key) {//直到找到
            current = current.next;
            if (current == null) {//没有找到
                return false;
            }
        }
        LinkD newLink = new LinkD(iData);
        if (current == last) {//最后一个节点
            newLink.next = null;
            last = newLink;
        } else {
            newLink.next = current.next;
            current.next.prev = newLink;
        }
        newLink.prev = current;
        current.next = newLink;
        return true;
    }

    /**
     * 删除指定的key
     * @param iData
     * @return
     */
    public LinkD deleteKey(int iData) {
        LinkD current = first;
        while (current.iData != iData) {
            current = current.next;//移动到下一个节点
            if (current == null) {
                return null;//没有找到
            }
        }
        if (current == first) { // 找到是第一个节点
            first = current.next;
        } else {
            current.prev.next = current.next;
        }
        // 上面关联前一个  下面关联后一个 维护2个节点
        if (current == last) {// 找到是最后一个
            last = current.prev;
        } else {
            current.next.prev = current.prev;
        }
        return current;
    }

    /**
     * 从前向后显示列表
     */
    public void displayForward() {
        System.out.println("List (first -- > last) :");
        LinkD current = first;
        while(current != null) {// 直到遍历到最后一个
            System.out.println(current.toString());
            current = current.next;
//            System.out.println("");
        }
    }

    /**
     * 从后向前遍历列表
     */
    public void displayBackward() {
        System.out.println("List (last -- > first) :");
        LinkD current = last;
        while(current != null) {
            System.out.println(current.toString());
            current = current.prev;
//            System.out.println("");
        }
    }

    public ListIterator getIterator() {
        return new ListIterator(this);
    }

    public LinkD getFirst() {
        return first;
    }

    public void setFirst(LinkD first) {
        this.first = first;
    }
}

class DoublyLinkListTest {
    public static void main(String[] args) {
        DoublyLinkList dll = new DoublyLinkList();
        dll.insertFirst(22);
        dll.insertFirst(66);
        dll.insertFirst(88);
        dll.insertLast(11);
        dll.insertLast(33);
        dll.insertLast(55);
        dll.displayForward();
        dll.displayBackward();
        dll.deleteFirst();
        dll.deleteLast();
        dll.displayForward();
        dll.deleteKey(11);
        dll.displayForward();

        dll.insertAfter(33,88);
        dll.insertAfter(22,55);
        dll.displayForward();
    }
}
