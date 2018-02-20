package java8.leasson2;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;

/**
 * @Author lx
 * @Date 2018/2/20 19:51
 */
public class DoneByYou {
    public static void main(String[] args) {
//        Function<String, Integer> stringToInteger = (String s) -> Integer.parseInt(s);
//        BiPredicate<List<String>, String> contains = (list, element) -> list.contains(element);

        Function<String, Integer> stringToInteger = Integer::parseInt;
        BiPredicate<List<String>, String> contains = List::contains;

    }
}
