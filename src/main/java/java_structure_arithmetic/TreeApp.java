package java_structure_arithmetic;

import java.io.IOException;
import java.util.Scanner;

/**
 * 树的操作类
 * Created by lx on 2016/9/10.
 */
public class TreeApp {
    public static void main(String[] args) throws IOException {
        Tree theTree = new Tree();
        theTree.insert(25, 1.5);
        theTree.insert(35, 1.6);
        theTree.insert(56, 1.7);
        theTree.insert(75, 1.2);
        theTree.insert(12, 1.7);
        theTree.insert(37, 1.5);
        theTree.insert(43, 1.7);
        theTree.insert(90, 1.6);
        theTree.insert(77, 1.7);
        theTree.insert(80, 1.2);
        theTree.insert(32, 1.7);
        theTree.insert(65, 1.5);
        theTree.insert(88, 1.7);

        int value;
        while (true) {
            System.out.println("请输入首字母：");
            System.out.println("show, insert, find, delete, or traverse:");
            int choice = getChar();
            switch (choice) {
                case 's':
                    theTree.displayTree();
                    break;
                case 'i':
                    System.out.println("请输入插入的值：");
                    value = getInt();
                    theTree.insert(value, value + 0.9);
                    break;
                case 'f':
                    System.out.println("请输入要查找的值：");
                    value = getInt();
                    Node found = theTree.find(value);
                    if (null != found) {
                        System.out.println("Found : ");
                        System.out.println(found);
                    } else {
                        System.out.println("没找到");
                        System.out.println(value + "\n");
                    }
                    break;
                case 'd':
                    System.out.println("请输入要删除的值");
                    value = getInt();
                    boolean didDelete = theTree.delete(value);
                    if (didDelete) {
                        System.out.println("Deleted " + value + "\n");
                    } else {
                        System.out.println("没找到");
                        System.out.println(value + "\n");
                    }
                    break;
                case 't':
                    System.out.println("输入类型 前序 ：1, 中序：2. 后序：3");
                    value = getInt();
                    theTree.traverse(value);
                    break;
                default:
                    System.out.println("无效的参数");
            }
        }
//        Node found = theTree.find(35);
//        if (null != found) {
//            System.out.println("找到了：" + found);
//        } else {
//            System.out.println("没有找到数据！");
//        }
    }

    /**
     * 获取用户台输入的字符串
     * @return
     * @throws IOException
     */
    public static String getString() throws IOException {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        return s;
    }

    /**
     * 获取用户台输入的字符
     * @return
     * @throws IOException
     */
    public static char getChar() throws IOException {
        return getString().charAt(0);
    }

    /**
     * 获取用户台输入的字符串
     * @return
     * @throws IOException
     */
    public static int getInt() throws IOException {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
}
