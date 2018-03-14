package java8_in_action.part2;

import java8_in_action.entiry.Apple;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: lx
 * @Date: Created in 2018/3/14 0014
 */
public class AppleFormatterTest {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80,"green"), new Apple(155, "green"), new Apple(120, "red"));
        prettyPrintApple(inventory, new AppleSimpleFormatter());

        prettyPrintApple(inventory, new AppleFancyFormatter());

        prettyPrintApple(inventory, (Apple a) -> {
            if ("green".equals(a.getColor())) {
                return "apple " + a.getColor() + " " + a.getWeight();
            }
            return "";
        });

        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });
        System.out.println(inventory);

        inventory.sort((Apple o1, Apple o2) -> o1.getWeight().compareTo(o2.getWeight()));
        System.out.println(inventory);

        inventory.sort((o1, o2) -> o1.getWeight().compareTo(o2.getWeight()));
        System.out.println(inventory);

        inventory.sort(Comparator.comparing(Apple::getWeight));
        System.out.println(inventory);
    }

    private static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter) {
        for (Apple apple : inventory) {
            String output = formatter.accept(apple);
            System.out.println(output);
        }
    }
}
