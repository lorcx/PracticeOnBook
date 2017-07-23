package java_structure_arithmetic;

import java.io.IOException;
import java.util.Scanner;

/**
 * hash表没有实现自动扩容 插入一定不能插入满了
 * Created by lx on 2016/9/17.
 */
public class HashApp2 {
    public static void main(String[] args) throws IOException {
        linkApp();
    }

    /**
     * 再hash法
     */
    public static void linkApp() throws IOException {
        Link aDataItem;
        int aKey, size, n,keysPerCell;
        keysPerCell = 10;
        System.out.println("请输入hash表的大小：");
        size = getInt();
        System.out.println("请输入初始数据项个数：");
        n = getInt();

        HashTable2 ht = new HashTable2(size);
        // 初始化数据
        for (int j = 0; j < n; j++) {
            aKey = (int) (Math.random() * keysPerCell * size);
            aDataItem = new Link(aKey,aKey);
            ht.insert(aDataItem);
        }

        while (true) {
            System.out.println("请输入下面单词的首字母：");
            System.out.println("show, insert, delete, find, exit:");
            char choice = getChar();
            switch (choice) {
                case 's':
                    ht.displayTable();
                    break;
                case 'i':
                    System.out.println("请输入插入的数据：");
                    aKey = getInt();
                    aDataItem = new Link(aKey,aKey);
                    ht.insert(aDataItem);
                    break;
                case 'd':
                    System.out.println("请输入要删除的数据：");
                    aKey = getInt();
                    ht.delete(aKey);
                    System.out.println("删除成功！");
                    break;
                case 'f':
                    System.out.println("请输入要查找的值：");
                    aKey = getInt();
                    aDataItem = ht.find(aKey);
                    if (aDataItem != null) {
                        System.out.println("找到 ：" + aDataItem.dData);
                    } else {
                        System.out.println("没有找到" + aKey);
                    }
                    break;
                case 'e':
                    System.out.println("<<<<<<<exit>>>>>>>");
                    System.exit(0);
                    break;
                default:
                    System.out.println("无效的输入项");
            }
        }
    }

    /**
     * 获取用户台输入的字符串
     *
     * @return
     * @throws java.io.IOException
     */
    public static String getString() throws IOException {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * 获取用户台输入的字符
     *
     * @return
     * @throws java.io.IOException
     */
    public static char getChar() throws IOException {
        return getString().charAt(0);
    }

    /**
     * 获取用户台输入的字符串
     *
     * @return
     * @throws java.io.IOException
     */
    public static int getInt() throws IOException {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
}
