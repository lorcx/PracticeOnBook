package java8_in_action.part6;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

/**
 * 质数收集器
 * @Author lx
 * @Date 2018/3/31 14:36
 */
public class PrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {
    /**
     * 生成器
     * @return
     */
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>() {{
            put(true, new ArrayList<>());
            put(false, new ArrayList<>());
        }};
    }

    /**
     * 累加器
     * @return
     */
    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> acc,
                Integer candicate) -> acc.get(isPrime(acc.get(true), candicate)).add(candicate);
    }

    /**
     * 用于实现并行
     * @return
     */
    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean, List<Integer>> map1,
                    Map<Boolean, List<Integer>> map2) -> {
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));
            return map1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    /**
     * 特征
     * @return
     */
    @Override
    public Set<Characteristics> characteristics() {
        // 因为该算法是顺序处理，所以无法实现并行
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }

    /**
     * 是否为质数
     * candidate 候选
     * @param primes
     * @param candidate
     * @return
     */
    private static boolean isPrime(List<Integer> primes, int candidate) {
        // 平方根
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return takeWhile(primes, p -> p <= candidateRoot)
                .stream()
                .noneMatch(p -> candidate % p == 0);
    }

    /**
     * 检查是否满足谓词，不满足截取一个子列表
     * @param list
     * @param p
     * @param <A>
     * @return
     */
    private static <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
        int i = 0;
        for (A item : list) {
            if (!p.test(item)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }
}
