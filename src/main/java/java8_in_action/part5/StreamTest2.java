package java8_in_action.part5;

import java8_in_action.entiry.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author lx
 * @Date 2018/3/24 16:59
 */
public class StreamTest2 {
    public static void main(String[] args) {
        /*
        筛选
        * */
        List<Dish> vegetarianMenu = Dish.menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());
        System.out.println("蔬菜菜单：" + vegetarianMenu);

        // 筛选偶数
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);

        // 筛选菜单前两个荤菜
        List<Dish> vegetarianMenu2 = Dish.menu.stream()
                .filter(d -> d.getType() == Dish.Type.MEAT)
                .limit(2)
                .collect(Collectors.toList());
        System.out.println("蔬菜菜单2：" + vegetarianMenu2);


        /**
         * 截断
         */
        List<Dish> dishes = Dish.menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(dishes);

        /**
         * 跳过
         */
        List<Dish> dishes1 = Dish.menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(Collectors.toList());
        System.out.println(dishes1);


        /**
         * 映射
         */
        List<Integer> dishNameLengths = Dish.menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println("每道菜的长度" + dishNameLengths);

        /*
            扁平化map
        * */
        String[] arrayOfWords = {"Hello", "World"};
        Stream<String> stringStream = Arrays.stream(arrayOfWords);// 将数组转成stream

        List<Stream<String>> collect = stringStream.map(word -> word.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());// 返回的是Stream<String>,不是期望的String

        List<String> collect1 = Arrays.stream(arrayOfWords)
                .map(word -> word.split("")) // 将每次单词分割字符数组
                .flatMap(Arrays::stream)// 将多个流合并成一个流
                .distinct()// 去重
                .collect(Collectors.toList());// 终端操作 执行

        System.out.println(collect1);

        // 练习1
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> collect2 = list.stream().map(n -> n * n).collect(Collectors.toList());
        System.out.println(collect2);

        // 练习2
        List<Integer> list2 = Arrays.asList(1, 2, 3);
        List<Integer> list3 = Arrays.asList(3, 4);

        // 命令式写法
        //for (Integer n : list2) {
        //    for (Integer n2 : list3) {
        //        System.out.print(n + "," + n2 + " ");
        //    }
        //    System.out.println();
        //}


        List<int[]> pair = list2.stream()
                .flatMap(i -> list3.stream().map(j -> new int[]{i, j}))// i 是入参 map是出参为stream
                .collect(Collectors.toList());
        pair.forEach(p -> System.out.println(Arrays.toString(p)));

        // 如果不用flatmap把多个流转换为一个流，而使用map就会返回一个stream
        //List<Stream<int[]>> collect3 = list2.stream()
        //        .map(i -> list3.stream().map(j -> new int[]{i, j}))
        //        .collect(Collectors.toList());


        System.out.println("至返回总和能被3整除的");
        List<int[]> pair2 = list2.stream()
                .flatMap(i -> list3.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[]{i, j}))// i 是入参 map是出参为stream
                .collect(Collectors.toList());
        pair2.forEach(p -> System.out.println(Arrays.toString(p)));

        /**
         * 查找和匹配
         */
        // anyMatch 至少匹配一个
        if (Dish.menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("菜单中有蔬菜");
        }

        // allMatch 全部匹配
        boolean isHealthy = Dish.menu.stream().allMatch(a -> a.getCalories() > 300);
        System.out.println("菜谱是否健康：" + isHealthy);

        // noneMatch都不匹配
        boolean isHealthy2 = Dish.menu.stream().noneMatch(a -> a.getCalories() < 300);
        System.out.println("菜谱中菜的开路里是否都大于等于300：" + isHealthy2);

        // findAny  返回一个Option<Dish> ifPresent如果存在就输出这个值
        Dish.menu.stream()
                .filter(Dish::isVegetarian)
                .findAny().ifPresent(d -> System.out.println(d.getName()));

        // findFirst
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> first = someNumbers.stream().map(x -> x * x).filter(x -> x % 3 == 0).findFirst();
        Integer integer = first.get();
        System.out.println(integer);


        /**
         * 规约
         */
        List<Integer> numbers2 = Arrays.asList(4, 9, 3, 9);
        Integer sum = numbers2.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        Integer subtract = numbers2.stream().reduce(0, (a, b) -> a - b);
        System.out.println(subtract);


        Integer sum2 = numbers2.stream().reduce(0, Integer::sum);
        System.out.println(sum2);

        Optional<Integer> op = numbers2.stream().reduce((a, b) -> a - b);
        System.out.println(op.get());

        // 最大值和最小值
        Optional<Integer> r1 = numbers2.stream().reduce(Integer::min);
        System.out.println(r1.get());
        Optional<Integer> r2 = numbers2.stream().reduce(Integer::max);
        System.out.println(r2.get());

        // 练习 用map reduce数有多少个菜

        Integer r3 = Dish.menu.stream().filter(Dish::isVegetarian).map(d -> 1).reduce(0, Integer::sum);
        System.out.println(r3);
    }
}
