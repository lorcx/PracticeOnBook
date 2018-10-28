package java8_in_action.part11.impl4;

import static java8_in_action.part11.impl4.Util.delay;
import static java8_in_action.part11.impl4.Util.format;

/**
 * 折扣
 * @Author lx
 * @Date 2018/10/27 16:05
 */
public class Discount {
    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        /**
         * 百分比
         */
        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    /**
     * 计算出折扣后价格
     *
     * @param quote
     * @return
     */
    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " Price is " + apply(quote.getPrice(), quote.getDiscountCode());
    }

    private static double apply(double price, Code code) {
        delay();
        return format(price * (100 - code.percentage) / 100);
    }
}
