package java_structure_arithmetic;

/**
 * 链表 (有序的 从小到大 单向链表)用于 hash表内部存储
 * Created by lx on 2016/9/17.
 */
public class SortedList {
    private Link first; // 第一个节点

    public SortedList() {
        this.first = null;
    }

    /**
     * 插入
     */
    public void insert(Link theLink) {
        int key = theLink.iData;
        Link previous = null;
        Link current = first;
        while (current != null && key > current.iData) {
            previous = current;
            current = current.next;
        }
        if (previous == null) { // 空的
            first = theLink;
        } else {
            previous.next = theLink;
        }
        theLink.next = current;
    }

    public void delete(int key) {
        Link previous = null;
        Link current = first;
        // 找到删除节点
        while (current != null && key != current.iData) {
            previous = current;
            current = current.next;// 移动到下一个节点
        }
        if (previous == null) { // 在开始节点
            first = first.next;
        } else { // 不再开始
            previous.next = current.next;
        }
    }

    public Link find(int key) {
        Link current = first;
        while (current != null && current.iData <= key) {
            if (current.iData == key) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void displayList() {
        System.out.println("List (first --> last):");
        Link current = first;
        while (current != null) {
            current.displayLink1();
            current = current.next;
        }
        System.out.println("");
    }
}
