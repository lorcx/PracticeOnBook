package java8.defaultMethod.conflict;

/**
 * @Author lx
 * @Date 2018/2/21 15:19
 */
public class App2 implements A, B, C {

    public static void main(String[] args) {
        new App2().sayHello();
    }

}