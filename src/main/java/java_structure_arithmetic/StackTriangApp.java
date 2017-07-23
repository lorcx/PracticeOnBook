package java_structure_arithmetic;

import java.io.IOException;
import java.util.Scanner;

/**
 * 将递归程序 改成 基于栈的程序
 * Created by lx on 2016/8/27.
 */
public class StackTriangApp {
    static int theNumber;
    static int theAnswer;
    static StackX<Params> theStack;
    static StackX<Integer> theIntStack;
    static int codePart;
    static Params theseParams;

    public static void main(String[] args) throws IOException {
        System.out.println("请输入一个数字:");
        theNumber = getInt();
//        recTriangle();
        stackTriangle();
        System.out.println("Triangle = " +  theAnswer);
    }

    private static void recTriangle() {
        theStack = new StackX<>(10000);
        codePart = 1;
        while (!step()) {

        }
    }

    /**
     * 步骤
     * @return
     */
    private static boolean step() {
        switch (codePart) {
            case 1:
                theseParams = new Params(theNumber, 6);
                theStack.push(theseParams);
                codePart = 2;
                break;
            case 2:
                theseParams = theStack.peek();
                if (theseParams.n == 1) {
                    theAnswer = 1;
                    codePart = 5;
                } else {
                    codePart = 3;
                }
                break;
            case 3:
                Params newParams = new Params(theseParams.n - 1, 4);
                theStack.push(newParams);
                codePart = 2;
                break;
            case 4:
                theseParams = theStack.peek();
                theAnswer = theAnswer + theseParams.n;
                codePart = 5;
                break;
            case 5:
                theseParams = theStack.peek();
                codePart = theseParams.returnAddress;
                theStack.pop();
                break;
            case 6:
                return true;
        }
        return false;
    }

    /**
     * 用简单的循环代替
     */
    public static void stackTriangle() {
        theIntStack = new StackX<>(10000);
        theAnswer = 0;
        while (theNumber > 0) {
            theIntStack.push(theNumber);
            --theNumber;
        }
        while (!theIntStack.isEmpty()) {
            Integer newN = theIntStack.pop();
            theAnswer += newN;
        }
    }

    public static String getString() throws IOException {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
}
