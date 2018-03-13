package java8_in_action.part1;

import javax.transaction.Transaction;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: lx
 * @Date: Created in 2018/3/13 0013
 */
public class GroupBy {
    public static void main(String[] args) {
        //java8之前
        /**
         * for...
         */

        //java8之后
        //Map<Currency, List<Transaction>> currencyListMap = transactions.stream()
        //        .filter((Transaction t) -> t.getPrice() > 1000)
        //        .collect(groupingBy(Transaction::getCurrency));
    }
}
