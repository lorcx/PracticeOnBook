package java_structure_arithmetic;

/**
 * ADT 抽象的数据类型
 * 着重做了什么，而不是怎么做的
 * 用链表实现《栈》
 * Created by lx on 2016/8/21.
 */
public class StackByLinkList {
    private LinkList linkList;

    public StackByLinkList() {
        this.linkList = new LinkList();
    }

    /**
     * 入栈
     */
    public void push(int iData, double dData) {
        linkList.insertFirst(iData, dData);
    }

    /**
     * 出栈
     * @return
     */
    public String pop() {
       Link link = linkList.deleteFirst();
       if(null == link) {
          return "";
       }
       return "data : { " + link.iData + ", " + link.dData + " }";
    }

    /**
     * 打印数据
     */
    public void printData(){
        linkList.displayList();
    }
}

class StackByLinkListTest {
    public static void main(String[] args) {
        StackByLinkList sbl = new StackByLinkList();
        sbl.push(10, 22.0d);
        sbl.push(22, 129.10d);
        sbl.push(36, 45.12d);
        sbl.push(44, 0.0d);

        sbl.printData();

        System.out.println("pop========================>>");

        System.out.println("pop ==>> " + sbl.pop());
        System.out.println("pop ==>> " + sbl.pop());
        System.out.println("pop ==>> " + sbl.pop());
        System.out.println("pop ==>> " + sbl.pop());

        sbl.printData();

    }
}