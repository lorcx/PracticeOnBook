package java8_in_action.part2;

import java8_in_action.entiry.Apple;

/**
 * Fancy 幻想
 *
 * @Author: lx
 * @Date: Created in 2018/3/14 0014
 */
public class AppleFancyFormatter implements AppleFormatter {
    @Override
    public String accept(Apple a) {
        String charachteristic = a.getWeight() > 150 ? "heavy " : "light ";
        return "A " + charachteristic + a.getColor() + " apple";
    }
}
