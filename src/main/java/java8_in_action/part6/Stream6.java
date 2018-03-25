package java8_in_action.part6;

import java8_in_action.entiry.Dish;
import java8_in_action.entiry.Trader;
import java8_in_action.entiry.Transaction;

import java.util.*;

import static java.util.stream.Collectors.*;
import static java8_in_action.entiry.Dish.menu;

/**
 * @Author lx
 * @Date 2018/3/25 14:44
 */
public class Stream6 {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        Map<Trader, List<Transaction>> map = transactions.stream().collect(groupingBy(Transaction::getTrader));
        System.out.println(map);

        // 查找流中最大和最小
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> dishOptional = Dish.menu.stream().collect(maxBy(dishCaloriesComparator)
        );
        System.out.println(dishOptional.get());

        // 汇总
        int totalCalories = Dish.menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println(totalCalories);

        // 求平均
        double avgCalories = Dish.menu.stream().collect(averagingDouble(Dish::getCalories));
        System.out.println(avgCalories);

        // 统计
        IntSummaryStatistics menuStatistics = Dish.menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);

        // 连接字符串
        String shortMenu = Dish.menu.stream().map(Dish::getName).collect(joining());
        System.out.println(shortMenu);

        String shortMenu2 = Dish.menu.stream().map(Dish::getName).collect(joining(", "));
        System.out.println(shortMenu2);

        chapter6_2();
    }

    /**
     * 规约
     */
    public static void chapter6_2() {
        // collect(reducing) 适用于可变的
        int totalCalories = menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
        System.out.println(totalCalories);

        // reducing 适用于不可变的 不变的规约
        Optional<Dish> mostCalories = menu.stream().reduce((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2);
        System.out.println(mostCalories.get());

        // 使用内置函数简化
        int totalCalories2 = menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
        System.out.println(totalCalories2);

        Optional<Integer> mostCalories2 = menu.stream().map(Dish::getCalories).reduce(Integer::max);
        System.out.println(mostCalories2.get());

        // 性能最好，可读性最好的
        int totalCalories3 = menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println(totalCalories3);
    }

}
