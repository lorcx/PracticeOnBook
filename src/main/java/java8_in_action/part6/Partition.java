package java8_in_action.part6;

import java8_in_action.entiry.Dish;

import javax.swing.text.html.Option;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import static java8_in_action.entiry.Dish.menu;
import static java.util.stream.Collectors.*;

/**
 * @Author: lx
 * @Date: Created in 2018/3/27 0027
 */
public class Partition {
    public static void main(String[] args) {
        // 分区是分组的一个特列 key 为 boolean
        Map<Boolean, List<Dish>> partitionMenu = menu.stream().collect(partitioningBy(Dish::isVegetarian));
        System.out.println(partitionMenu);

        List<Dish> collect = menu.stream().filter(Dish::isVegetarian).collect(toList());

        // 二级map
        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishByType = menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType, toList())));
        System.out.println(vegetarianDishByType);

        Map<Boolean, Dish> mostCaloriesPartitionedByVegetarian = menu.stream().collect(partitioningBy(Dish::isVegetarian,
                collectingAndThen(maxBy(Comparator.comparing(Dish::getCalories)), Optional::get)
        ));
        System.out.println(mostCaloriesPartitionedByVegetarian);

        // 将数字按质数和非质数分区
        Map<Boolean, List<Integer>> primes = new Partition().partitionPrimes(100);
        System.out.println(primes);
    }

    public Map<Boolean, List<Integer>> partitionPrimes(int candidate) {
        return IntStream.rangeClosed(2, candidate).boxed()
                .collect(partitioningBy(x -> isPrime(x)));
    }

    public boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }
}
