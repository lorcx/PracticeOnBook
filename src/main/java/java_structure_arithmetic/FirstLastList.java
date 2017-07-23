package java_structure_arithmetic;

/**
 * 双端链表 （它不是双向链表）
 * 链头 有链表尾部的引用
 * Created by lx on 2016/8/21.
 */
public class FirstLastList {
    private Link first;
    private Link last;

    public FirstLastList() {
        this.first = null;
        this.last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    /**
     * 插入
     * @param iData
     * @param dData
     */
    public void insertFirst(int iData,double dData) {
        Link newLink = new Link(iData, dData);
        if(isEmpty()) {
           last = newLink;//newLink --> last
        }
        /*
          在开头插入 那么
           新的下一个 就是 上一个
         */
        newLink.next = first;//newLink.next --> oldLink
        /*
          第一个就是最新的节点
         */
        first = newLink;// first --> newLink
    }

    /**
     * 插入最后节点
     * @param iData
     * @param dData
     */
    public void insertLast(int iData,double dData){
        Link newLink = new Link(iData, dData);
        if(isEmpty()) {
            /*
              插入的是最后一个节点，他没有子节点 所以
             */
            first = newLink;
        } else {
            /*
              旧的最后一个 连接 新的最后一个
             */
            last.next = newLink;
        }
        /*  oldLast  newLast
            [33] --> [22]
           last
            [22] -- > ?
            将新的作为最后一个节点
        */
        last = newLink;
    }

    /**
     * 删除
     * @return
     */
    public Link deleteFirst() {
        Link temp = first;
        if(first.next == null) {
            last = null;
        }
        /*
         从头删就把 当前的下一个最为头就可以了
         */
        first = first.next;
        return temp;
    }

    /**
     * 显示列表
     */
    public void displayList() {
        System.out.println("List (first --> last)");
        Link current = first;
        while(null != current) {
            System.out.println(current.displayLink());
            current = current.next;
        }
    }
}

class FirstLastListTest {
    public static void main(String[] args) {
        FirstLastList fll = new FirstLastList();
        fll.insertFirst(22,12.0d);
        fll.insertFirst(564,54.12d);
        fll.insertFirst(45,1.2d);
        fll.displayList();

        fll.insertLast(32,43.32d);
        fll.insertLast(65,343.1d);
        fll.insertLast(87,454.1d);

        fll.displayList();

        fll.deleteFirst();
        fll.deleteFirst();

        fll.displayList();

    }
}