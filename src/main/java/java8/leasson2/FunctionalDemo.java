package java8.leasson2;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.*;

/**
 * @Author lx
 * @Date 2018/2/19 17:47
 */
public class FunctionalDemo {

    /**
     * 断言 传入参数，返回布尔
     */
    public void predicate() {
        Predicate<String> namesStartingWithS = name -> name.startsWith("s");
//        boolean hello = namesStartingWithS.test("Hello");
        boolean hello = namesStartingWithS.test("sHsello");
        System.out.println(hello);
    }

    /**
     * 消费 传入参数，但没有返回值
     */
    public void consumer() {
        Consumer<String> messageConsumer = message -> System.out.println(message);
        messageConsumer.accept("Learn java 8");
    }

    /**
     * 提供数据 没有参数传入，但有返回值
     */
    public void supplier() {
        Supplier<String> uuidGenerator = () -> UUID.randomUUID().toString();
        System.out.println(uuidGenerator.get());
    }

    public static void main(String[] args) {
        FunctionalDemo fd = new FunctionalDemo();
        fd.predicate();
        fd.consumer();
        fd.supplier();

        List<Integer> list = new ArrayList<>();
        for (int i = 300; i < 400; i++) {
            list.add(i);
        }

        // 断言一个数是不是偶数
        IntPredicate evenNumbers = (int i) -> i % 2 == 0;
        boolean b1 = evenNumbers.test(1000);
        System.out.println(b1);

        // function 是由一种类型转换为另一种类型
        Function<Integer, Integer> add1 = x -> x + 1;
        Integer two = add1.apply(1);
        Integer three = add1.apply(2);
        System.out.println(two);
        System.out.println(three);

        Function<String, String> concat = x -> x + "abc";
        String answer = concat.apply("hhh");
        System.out.println(answer);

        BinaryOperator<Integer> sum = (a, b) -> a + b;
        Integer res = sum.apply(1, 2);
        System.out.println(res);

        /**
         * 第一个apply执行的是 (f, g) -> {...}
         * 第二个apply执行的是 x -> {...}
         * f = i1 -> i1 + 1
         * g = i2 -> i2 + 2
         */
        BinaryOperator<Function<Integer, Integer>> compose = (f, g) -> x -> g.apply(f.apply(x));
        Integer n1 = compose.apply(i1 -> i1 + 1, i2 -> i2 + 2).apply(10);
        System.out.println("复杂表达式返回：" + n1);

        UnaryOperator<Integer> add2 = n -> n + 1;
        UnaryOperator<String> concat1 = s -> s + 1;
        String bbb = concat1.apply("bbb");
//        System.out.println(bbb);

        Function<Integer, UnaryOperator<Integer>> sum2 = x -> y -> x + y;
        UnaryOperator<Integer> apply = sum2.apply(10);
        Integer apply1 = apply.apply(20);
//        System.out.println(apply1);

    }
}
