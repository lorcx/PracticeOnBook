package java8_in_action.part8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author lx
 * @Date 2018/4/7 13:14
 */
public class Test1 {
    public static void main(String[] args) {
        doSomething((Task) () -> System.out.println("abc"));


        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> result = numbers.stream()
                .peek(x -> System.out.println("from stream : " +x))
                .map(x -> x + 17)
                .peek(x -> System.out.println("after map: " + x))
                .filter(x -> x % 2 ==0)
                .peek(x -> System.out.println("after filter:" + x))
                .limit(3)
                .peek(x -> System.out.println("after limit:" + x))
                .collect(Collectors.toList());
        System.out.println(result);
    }

    public static void doSomething(Runnable r) {
        r.run();
    }

    public static void doSomething(Task t) {
        t.execute();
    }


}
