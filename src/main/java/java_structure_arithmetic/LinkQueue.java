package java_structure_arithmetic;

/**
 * 双端链表实现队列
 * Created by lx on 2016/8/21.
 */
public class LinkQueue {
    private FirstLastList firstLastList;

    public LinkQueue() {
        this.firstLastList = new FirstLastList();
    }

    /**
     * 入列
     */
    public void insert(int iData, double dData) {
        firstLastList.insertLast(iData, dData);//从队尾插入 rear
    }

    /**
     * 出列
     *
     * @return
     */
    public String remove() {
        Link link = firstLastList.deleteFirst(); //从对头取出 front
        if (null == link) {
            return "";
        }
        return "data : { " + link.iData + ", " + link.dData + " }";
    }

    /**
     * 是否为空
     */
    public boolean isEmpty() {
        return firstLastList.isEmpty();
    }

    /**
     * 打印列表
     */
    public void printQueue() {
        firstLastList.displayList();
    }
}

class LinkQueueTest {
    public static void main(String[] args) {
        LinkQueue lq = new LinkQueue();
        lq.insert(10, 20.32d);
        lq.insert(54, 234.3d);
        lq.insert(67, 456.1d);

        lq.printQueue();

        lq.remove();
        lq.remove();

        lq.printQueue();
    }
}