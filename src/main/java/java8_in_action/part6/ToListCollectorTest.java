package java8_in_action.part6;

import java8_in_action.entiry.Dish;
import java.util.List;

/**
 * @Author lx
 * @Date 2018/3/31 14:02
 */
public class ToListCollectorTest {
    public static void main(String[] args) {
        List<Dish> allList = Dish.menu;
        List<Dish> dishList = Dish.menu.stream().filter(d -> d.getCalories() > 300).collect(new ToListCollector<>());
        System.out.println(allList);
        System.out.println(dishList);
    }
}
