package java8.defaultMethod.conflict;

/**
 * @Author lx
 * @Date 2018/2/21 15:19
 */
public class App implements A {

    @Override
    public void sayHello(){
        System.out.println("你好，我是 APP");
    }

    public static void main(String[] args) {
        new App().sayHello();
    }

}