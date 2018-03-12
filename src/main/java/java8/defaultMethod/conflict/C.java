package java8.defaultMethod.conflict;

/**
 * @Author lx
 * @Date 2018/2/21 15:19
 */
public interface C extends A {

    default void sayHello(){
        System.out.println("你好，我是 渣渣辉");
    }

}
