package java_structure_arithmetic;

import java.util.Scanner;

/**
 * 递归算法
 * 输出三角数字型
 * 如果是阶乘 把+改成*就可以了
 * Created by lx on 2016/8/27.
 */
public class TriangleApp {
    static int theNumber;

    public static void main(String[] args) {
        System.out.println("请输入一个数字：");
        theNumber = getInt();
        int theAnswer = triangle(theNumber);
        System.out.println("结果是：" + theAnswer);
    }

    private static int triangle(int theNumber) {
        System.out.println("entering: n = " + theNumber);
        if (theNumber == 1) {
            System.out.println("returning: 1");
            return 1;
        } else {
            int temp = theNumber + triangle(theNumber - 1);
            System.out.println("returning:" + temp);
            return temp;
        }
    }


    public static int getInt() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    /**
     *   1: *
     *   2: *
     *      **
     *   3: *
     *      **
     *      ***
     *   4:*
     *     **
     *     ***
     *     ****
     *
     */
}
