package java_structure_arithmetic;

import java.util.Arrays;

/**
 * 单端链表
 * Created by lx on 2016/8/21.
 */
public class LinkList {
    private Link first;//用来定位链表所有的节点

    public LinkList() {
        first = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    /**
     * 插入新的节点
     */
    public void insertFirst(int iData, double dData) {
        Link newLink = new Link(iData, dData);
        newLink.next = first; //newLink ---> oldLink
        first = newLink; //first --> newLink
    }

    /**
     * 删除头节点
     *
     * @return
     */
    public Link deleteFirst() {
        //jvm 清理
        Link temp = first;
        first = first.next;
        return temp;
    }

    /**
     * 显示链表
     */
    public void displayList() {
        System.out.println("first --> last");
        Link current = first;
        while (null != current) {
            System.out.println(current.displayLink());
            current = current.next;
        }
        System.out.println("end ...");
    }


    /**
     * 查找
     */
    public Link find(int key) {
        Link current = first;
        while (current.iData != key) {
            if (current.next == null) {
                return null;
            } else {
                current = current.next;
            }
        }
        return current;
    }


    /**
     * 删除
     */
    public Link deleteByKey(int key) {
        Link current = first;//当前节点
        Link previous = first;//上一节点
        while (current.iData != key) {
            if (current.next == null) {//最后一个节点
                return null;
            } else {
                previous = current;
                current = current.next;
            }
        }
        if(current == first) {
            first = first.next;
        } else {
            previous.next = current.next;//画个图就好理解了
        }
        return current;
    }

    /**
     * 《有序链表》
     * 有序插入
     */
    public void orderInsert(int iData, double dData) {
        Link newLink = new Link(iData, dData);
        Link previous = null;//上一节点
        Link current = first;//下一节点
        //从小到大排序
        while(current != null && iData > current.iData) {
            previous = current;
            current = current.next;//移动下个节点
        }
        if(previous == null) {//插入在开头
            first = newLink;
        } else {
            previous.next = newLink;
        }
        newLink.next = current;
    }

    /**
     * 对数组排序
     */
    public void sortArr(Link[] arr) {
        for (Link l : arr) {
            orderInsert(l.iData, l.dData);
        }


    }

}

class LinkListTest {
    public static void main(String[] args) {
        LinkList linkList = new LinkList();
//        linkList.insertFirst(10, 22.22d);
//        linkList.insertFirst(20, 11.22d);
//        linkList.insertFirst(76, 12.2d);
//        linkList.insertFirst(87, 0.2d);
//        linkList.displayList();


//        linkList.orderInsert(87, 0.2d);
//        linkList.orderInsert(20, 11.22d);
//        linkList.orderInsert(10, 22.22d);
//        linkList.orderInsert(76, 12.2d);

        /*******************表插入排序 begin  (数组-》 链表 -》 数组)****************************/
        Link[] arr = {new Link(87,0.2d),new Link(20,0.2d), new Link(10,22.22d) , new Link(76,12.2d)};
        linkList.sortArr(arr);

        for(int i = 0; i < arr.length; i++){
            arr[i] = linkList.deleteFirst();//从链表中复制到数组
        }
        System.out.println(Arrays.toString(arr));
        /*******************表插入排序 end****************************/
//        while (!linkList.isEmpty()) {
//            Link aLink = linkList.deleteFirst();
//            System.out.println("Deleted ");
//            System.out.println(aLink.displayLink());
//        }

        linkList.displayList();

//        Link f = linkList.find(20);
//        if (null != f) {
//            System.out.println(" 找到节点： " + f);
//        } else {
//            System.out.println(" 没找到节点： ");
//        }
//
//        Link d = linkList.deleteByKey(76);
//        if(null != d) {
//            System.out.println("删除节点 ：" + d);
//        } else {
//            System.out.println(" 该节点不存在 ");
//        }
//
//        linkList.displayList();
    }

}