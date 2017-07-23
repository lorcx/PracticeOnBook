package java_structure_arithmetic;

import java.io.IOException;
import java.util.Scanner;

/**
 * 迭代器
 * Created by lx on 2016/8/27.
 */
public class ListIterator {
    private LinkD current;
    private LinkD prev;
    private DoublyLinkList dll;

    public ListIterator(DoublyLinkList dll) {
        this.dll = dll;
    }

    public void reset() {
        current = dll.getFirst();
        prev = null;
    }

    public boolean atEnd() {
        return current.next == null;
    }

    public LinkD getCurrent() {
        return current;
    }

    /**
     * 移动到下个节点
     */
    public void newLink() {
        prev = current;
        current = current.next;
    }

    public void insertAfter(int iData) {
        LinkD newLink = new LinkD(iData);
        if (dll.isEmpty()) {
            dll.setFirst(newLink);
            current = newLink;
        } else {
            newLink.next = current.next;
            current.next = newLink;
            newLink();
        }

    }

    /**
     * 插入到之前
     */
    public void insertBefore(int iData) {
        LinkD newLink = new LinkD(iData);
        if (prev == null) {
            newLink.next = dll.getFirst();
            dll.setFirst(newLink);
            reset();
        } else {
            newLink.next = prev.next;
            prev.next = newLink;
            current = newLink;
        }
    }

    public long deleteCurrent() {
        long value = current.iData;
        if (prev == null) {
            dll.setFirst(current.next);
            reset();
         } else {
            prev.next = current.next;
            if (atEnd()) {
                reset();
            } else {
                current = current.next;
            }
        }
        return value;
    }
}

class ListIteratorTest {
    public static void main(String[] args) throws IOException {
        DoublyLinkList dll = new DoublyLinkList();
        ListIterator iter1 = dll.getIterator();
        long value;
        iter1.insertAfter(20);
        iter1.insertAfter(40);
        iter1.insertAfter(80);
        iter1.insertAfter(60);

        while(true) {
            System.out.println("按 first 或 show , reset,");
            System.out.println("next, get, before, aftrer, delet:");
            System.out.flush();
            int choice = getChar();
            switch (choice) {
                case 's':
                    if (!dll.isEmpty()) {
                        dll.displayForward();
                    } else {
                        System.out.println(" List 是空");
                    }
                    break;
                case 'r':
                    iter1.reset();
                    break;
                case 'n': //移动到下一项
                    if (!dll.isEmpty() && !iter1.atEnd()) {
                        iter1.newLink();
                    } else {
                        System.out.println("不能移动到下一个节点 ");
                    }
                    break;
                case 'b': //插入到节点之后
                    System.out.println("请输入要插入的值：");
                    System.out.flush();
//                    long valueL = Long.valueOf(String.valueOf(value));
                    iter1.insertBefore(getInt());
                    break;
                case 'a':
                    System.out.println("请输入要插入的值");
                    System.out.flush();
                    iter1.insertAfter(getInt());
                    break;
                case 'd':
                    if (!dll.isEmpty()) {
                        value = iter1.deleteCurrent();
                        System.out.println("删除了 ：" + value);
                    } else {
                        System.out.println(" 不能删除");
                    }
                    break;
                default:
                    System.out.println("无效的操作");
            }
        }
    }

    public static String getString() throws IOException {
//        InputStreamReader isr = new InputStreamReader(System.in);
//        BufferedReader br = new BufferedReader(isr);
//        String s = br.readLine();
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        return s;
    }

    public static char getChar() throws IOException{
        String s = getString();
        return s.charAt(0);
    }

    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
}
