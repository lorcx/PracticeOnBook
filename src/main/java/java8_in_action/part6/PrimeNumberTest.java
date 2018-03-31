package java8_in_action.part6;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.partitioningBy;

/**
 * @Author lx
 * @Date 2018/3/31 14:55
 */
public class PrimeNumberTest {
    public static void main(String[] args) {
        /* 比较收集器性能
         */
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            //partitionPrimes2(1_000_000);
            partitionPrimes1(1_000_000);// 自定义收集器 性能提升62%
            long duration = (System.nanoTime() - start) / 1_000_000;//duration持续
            // 计算最快的毫秒数
            if (duration < fastest) {
                fastest = duration;
            }
        }
        System.out.println("最快毫秒：" + fastest);
    }
    private static Map<Boolean, List<Integer>> partitionPrimes1(int candidate) {
        return IntStream.rangeClosed(2, candidate).boxed()
                .collect(new PrimeNumbersCollector());
    }

    private static Map<Boolean, List<Integer>> partitionPrimes2(int candidate) {
        return IntStream.rangeClosed(2, candidate).boxed()
                .collect(partitioningBy(x -> isPrime(x)));
    }

    private static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }
}
