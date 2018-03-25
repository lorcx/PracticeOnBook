package java8_in_action.part4;

import java8_in_action.entiry.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: lx
 * @Date: Created in 2018/3/20 0020
 */
public class StreamTest {
    public static void main(String[] args) {
        /*
        * Dish.menu 相当于元素序列
        * filter、map、limit相当于流水线，执行这些操作只是加入到流水线中并没有执行
        * collect最终执行
        * */
        List<String> threeHighCaloricDishName = Dish.menu.stream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(threeHighCaloricDishName);


        // 流只能被遍历一次
        List<String> title = Arrays.asList("java", "IKN", "qwe");
        Stream<String> s = title.stream();
        s.forEach(System.out::print);
        // tream has already been operated upon or closed
        //s.forEach(System.out::print);


        splitPrint();
    }

    /**
     * 打印每个stream操作
     */
    public static void splitPrint() {
        /*
        * filter和mapping -> 循环合并
        * */
        List<String> threeHighCaloricDishName = Dish.menu.stream()
                .filter(d -> {
                    System.out.println("filter : " + d.getName());
                    return d.getCalories() > 300;
                })
                .map(d -> {
                    System.out.println("mapping : " + d.getName());
                    return d.getName();
                })
                .limit(3)
                .collect(Collectors.toList());
        //System.out.println(threeHighCaloricDishName);
    }
}
