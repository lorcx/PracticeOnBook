package java8_in_action.part2;

import java8_in_action.entiry.Apple;

/**
 * @Author: lx
 * @Date: Created in 2018/3/14 0014
 */
public class AppleSimpleFormatter implements AppleFormatter {
    @Override
    public String accept(Apple a) {
        return "An apple of " + a.getWeight() + "g";
    }
}
