package java8.defaultMethod;

/**
 * @Author lx
 * @Date 2018/2/21 15:25
 */
public class UseCalc {
    public static void main(String[] args) {

        Calculator cal = Calculator.getInstance();
        System.out.println(cal.mod(3, 2));
    }
}
