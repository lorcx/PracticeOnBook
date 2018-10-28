package java8_in_action.part10;

import java.util.Optional;
import static java.util.Optional.*;


/**
 * @Author lx
 * @Date 2018/10/27 11:32
 */
public class OperationsWithOptional {
    public static void main(String[] args) {
        System.out.println(max(of(3), of(5)).get());
        System.out.println(max(empty(), of(5)).orElse(0));
    }

    public static final Optional<Integer> max(Optional<Integer> i, Optional<Integer> j) {
        return i.flatMap(a -> j.map(b -> Math.max(a, b)));
    }
}

