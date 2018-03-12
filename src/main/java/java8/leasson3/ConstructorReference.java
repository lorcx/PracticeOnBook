package java8.leasson3;

import java.util.function.Supplier;

/**
 * 构造器引用
 * 对于一个现有构造器函数，你可以利用它的名字和关键字来创建一个它的引用:ClassName::new
 * 它的功能与指向静态方法的引用类似
 * supplier提供
 *
 * @Author lx
 * @Date 2018/2/20 19:39
 */
public class ConstructorReference {
    public static void main(String[] args) {
        // 根据参数列表自动匹配构造器
        Supplier<ConstructorReference> sup = ConstructorReference::new;
        ConstructorReference constructorReference = sup.get();

    }
}
