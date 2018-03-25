package java8_in_action.part5;

import java8_in_action.entiry.Trader;
import java8_in_action.entiry.Transaction;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author lx
 * @Date 2018/3/24 20:34
 */
public class lianxi {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // 1. 找出2011年发生的交易，并按交易额顺序排序（从低到高） ✔
        List<Transaction> c1 = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(c1);

        // 2. 交易员都在那些不同的城市工作过 ✔
        List<String> c2 = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(c2);

        // 新招 利用set去重
        Set<String> c21 = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .collect(Collectors.toSet());
        System.out.println(c21);

        // 3.找出所有来自剑桥交易所的交易员，并按姓名排序
        List<Trader> c3 = transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getTrader)
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(c3);

        // 4.返回所有交易员名字的字符串，按字母排序
        String c4 = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);
        System.out.println(c4);

        // joining内部使用了stringbuilder效率高
        String c41 = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining());
        System.out.println(c41);

        // 5. 有没有交易员是在米兰工作 ✔
        boolean isMelan = transactions.stream().anyMatch(t -> t.getTrader().getCity().equals("Milan"));
        System.out.println(isMelan);

        // 6. 打印生活在剑桥的交易员的所有金额 ✔
        transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        // 7. 找出所有交易额中最高点交易额
        Integer max = transactions.stream().map(Transaction::getValue)
                .reduce(0, Integer::max);
        System.out.println("最大交易额" + max);

        // 8. 找到最小交易额的交易
        Optional<Transaction> re = transactions.stream()
                .reduce((Transaction t1, Transaction t2) ->  t1.getValue() < t2.getValue() ? t1 : t2);
        System.out.println(re.get());

        // 更简单的做法
        Optional<Transaction> re2 = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));
        System.out.println(re2.get());


    }
}
