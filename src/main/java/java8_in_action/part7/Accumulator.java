package java8_in_action.part7;

/**
 * @Author lx
 * @Date 2018/4/6 21:29
 */
public class Accumulator {
    long total = 0;

    public void add(long value) {
        total += value;
    }

}
