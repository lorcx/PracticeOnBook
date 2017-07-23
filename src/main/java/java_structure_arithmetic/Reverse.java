package java_structure_arithmetic;

import java.util.Scanner;

/**
 * 栈应用 ： 单词逆序
 * Created by lx on 2016/8/20.
 */
public class Reverse {
    private StackX<Character> sx;
    private String input;
    private int len;


    public Reverse(String input) {
        this.input = input;
        len = input.length();
        sx = new StackX<Character>(len);
    }

    public String doDev() {
         String output = "";
         for (int i = 0; i < len; i++) {
            if(!sx.isFull()) {
                sx.push(input.charAt(i));
            }
         }
         while(!sx.isEmpty()) {
             output = output + sx.pop();
         }
         return output;
    }
}

class ReverseTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        Reverse reverse = new Reverse(str);
        System.out.println("============单词反转==============");
        System.out.println(reverse.doDev());
    }

}