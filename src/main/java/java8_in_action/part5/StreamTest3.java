package java8_in_action.part5;

import java8_in_action.entiry.Dish;

import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author lx
 * @Date 2018/3/24 21:35
 */
public class StreamTest3 {
    public static void main(String[] args) {
        // 数值流
        int calories = Dish.menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println(calories);

        // 数值流转非特化流
        IntStream intStream = Dish.menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> boxed = intStream.boxed();

        OptionalInt max = Dish.menu.stream().mapToInt(Dish::getCalories).max();
        int i = max.orElse(1);// 如果不存在则显示1，否则显示对应的值0
        System.out.println(i);

        // 范围
        IntStream eventNumbers = IntStream.rangeClosed(1, 100)// rangeClosed包含结尾
                .filter(x -> x % 2 == 0);
        System.out.println(eventNumbers.count());
        IntStream eventNumbers1 = IntStream.range(1, 100)// range不包含结尾
                .filter(x -> x % 2 == 0);
        System.out.println(eventNumbers1.count());

        System.out.println(2 % 3);

        /**
         * 勾股流
         *  a *a + b*b = c*c （整数）
         *  假如给定a、b求出c
         */
        // 1. 验证a和b构成的勾股流是不是整数
        //filter((a, b) -> Math.sqrt(a * a + b * b) % 1 == 0);

        // 2. 生成三元组
        //stream.filter((a, b) -> Math.sqrt(a * a + b * b) % 1 == 0)
        //        .map((a, b) -> new int[] {a, b, Math.sqrt(a * a + b * b)});

        // 3. 给b数
        //IntStream.rangeClosed(1, 100)
        //        .filter((a, b) -> Math.sqrt(a * a + b * b) % 1 == 0)
        //        .boxed() // intStream的map只会返回一个int，而结果想要一个int[]
        //        .map((a, b) -> new int[] {a, b, Math.sqrt(a * a + b * b)});

        // 4. 改写上边例子
        //IntStream.rangeClosed(1, 100)
        //        .filter((a, b) -> Math.sqrt(a * a + b * b) % 1 == 0)
        //        .mapToObj((a, b) -> new int[] {a, b, Math.sqrt(a * a + b * b)});

        //5. 生成值
        Stream<int[]> ggstream = IntStream.rangeClosed(1, 100)
                .boxed() // 如果没有转成非数值流 就会返回 Stream<Integer>
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );
        // 6. 运行代码
        ggstream.limit(5).forEach(arr -> System.out.println(arr[0] + "," + arr[1] + "," + arr[2]));
        System.out.println("优化后");
        // 8. 优化代码
        Stream<double[]> ggstream1 = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                        .filter(arr -> arr[2] % 1 == 0)
                );
        ggstream1.limit(5).forEach(arr -> System.out.println(arr[0] + "," + arr[1] + "," + arr[2]));
    }
}
