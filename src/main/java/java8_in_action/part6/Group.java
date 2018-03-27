package java8_in_action.part6;

import java8_in_action.entiry.Dish;

import java.util.*;

import static java8_in_action.entiry.Dish.menu;
import static java.util.stream.Collectors.*;

/**
 * @Author: lx
 * @Date: Created in 2018/3/27 0027
 */
public class Group {
    enum CaloricLevel {DIET, NORMAL, FAT}

    public static void main(String[] args) {
        // 1级分组
        Map<Dish.Type, List<Dish>> typeListMap = menu.stream().collect(groupingBy(Dish::getType));
        System.out.println(typeListMap);
        // 上面的完整版
        Map<Dish.Type, List<Dish>> typeListMap2 = menu.stream().collect(groupingBy(Dish::getType, toList()));
        System.out.println(typeListMap2);

        // 多级分组
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> typeMap2 = menu.stream().collect(groupingBy(Dish::getType, groupingBy(dish -> {
            if (dish.getCalories() <= 400) {
                return CaloricLevel.DIET;
            } else if (dish.getCalories() <= 700) {
                return CaloricLevel.NORMAL;
            } else {
                return CaloricLevel.FAT;
            }
        })));

        System.out.println(typeMap2);

        // 按自组收集
        Map<Dish.Type, Long> typeLongMap = menu.stream().collect(groupingBy(Dish::getType, counting()));
        System.out.println(typeLongMap);

        // 收集热量最高的, 返回有值才映射到optional上，所以optional不可能出现empty
        Map<Dish.Type, Optional<Dish>> typeOptionalMap = menu.stream().collect(groupingBy(Dish::getType, maxBy(Comparator.comparing(Dish::getCalories))));
        System.out.println(typeOptionalMap);

        // 收集热量最好并带有转换函数  collectingAndThen 第一个参数时分组函数，第二个参数是转换函数
        Map<Dish.Type, Dish> typeDishMap = menu.stream().collect(groupingBy(Dish::getType, collectingAndThen(maxBy(Comparator.comparing(Dish::getCalories)), Optional::get)));
        System.out.println(typeDishMap);

        // 与groupby联合收集的例子
        Map<Dish.Type, Set<CaloricLevel>> caloricLevelByType = menu.stream().collect(groupingBy(Dish::getType, mapping(dish -> {
            if (dish.getCalories() <= 400) {
                return CaloricLevel.DIET;
            } else if (dish.getCalories() <= 700) {
                return CaloricLevel.NORMAL;
            } else {
                return CaloricLevel.FAT;
            }
        }, toSet())));
        System.out.println(caloricLevelByType);

        // 求每个分类的总和
        Map<Dish.Type, Integer> totalCaloriesByType = menu.stream().collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
        System.out.println(totalCaloriesByType);

        // 设置指定的收集类
        Map<Dish.Type, TreeSet<CaloricLevel>> caloricLevelByType2 = menu.stream().collect(groupingBy(Dish::getType, mapping(dish -> {
            if (dish.getCalories() <= 400) {
                return CaloricLevel.DIET;
            } else if (dish.getCalories() <= 700) {
                return CaloricLevel.NORMAL;
            } else {
                return CaloricLevel.FAT;
            }
        }, toCollection(TreeSet::new))));
        System.out.println(caloricLevelByType2);



    }
}
