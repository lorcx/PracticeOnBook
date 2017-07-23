package java_structure_arithmetic;

import java.io.IOException;
import java.util.Scanner;

/**
 * 变位词  输入一个单词 显示所有的排列组合
 * Created by lx on 2016/8/27.
 */
public class AnagramApp {
    static int size; //输入单词的总大小
    static int count;
    static char[] arrChar = new char[100];// 存储数据

    public static void main(String[] args) throws IOException {
        System.out.println("请输入一个单词：");
        String input = getString();
        size = input.length();
        count = 0;
        // 存储到数组中
        for (int i = 0; i < size; i++) {
            arrChar[i] = input.charAt(i);
        }
        doAnagarm(size);
    }

    private static void doAnagarm(int newSize) {
        if (newSize == 1) {
            return;
        }
        for (int j = 0; j < newSize; j++) {
            doAnagarm(newSize - 1);
            if (newSize == 2) {
               displayWord();
            }
//            System.out.println("newSize = " + newSize);
            rotate(newSize);
        }
    }

    /**
     * 将指定位置的元素移到开头 ，并且他后边的都外后移动一位
     * @param newSize
     */
    private static void rotate(int newSize) {
        int j;
        int position = size - newSize;
        char temp = arrChar[position];
        for (j = position + 1; j < size; j++) {
            arrChar[j - 1] = arrChar[j];
        }
        arrChar[j - 1] = temp;
    }

    /**
     * 显示单词
     */
    private static void displayWord() {
        if (count < 99) {
            System.out.print(" ");
        }
        if (count < 9) {
            System.out.print(" ");
        }
        System.out.print(++count + " ");
        for (int j = 0; j < size; j++) {
            System.out.print(arrChar[j]);
        }
        System.out.print("  ");
        if (count % 6 == 0) {
            System.out.println("");
        }
    }

    public static String getString() throws IOException {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        return s;
    }
}
