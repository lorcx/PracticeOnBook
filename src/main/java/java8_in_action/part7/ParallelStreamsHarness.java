package java8_in_action.part7;

import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;

/**
 * harness治理
 * @Author lx
 * @Date 2018/4/6 21:33
 */
public class ParallelStreamsHarness {
    public static final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool();

    public static void main(String[] args) {
        System.out.println("顺序迭代：" + measurePref(ParallelStreams::iterativeSum, 1_000_000L));
        System.out.println("流顺序迭代：" + measurePref(ParallelStreams::sequentialSum, 1_000_000L));
        System.out.println("流并发迭代：" + measurePref(ParallelStreams::parallelSum, 1_000_000L));
        System.out.println("基本类型-流顺序迭代：" + measurePref(ParallelStreams::rangeSum, 1_000_000L));
        System.out.println("基本类型-流并发迭代：" + measurePref(ParallelStreams::parallelRangeSum, 1_000_000L));
        System.out.println("共享变量顺序迭代：" + measurePref(ParallelStreams::sideEffectSum, 1_000_000L));
        System.out.println("共享变量并发迭代：" + measurePref(ParallelStreams::sideEffectParallelSum, 1_000_000L));
        System.out.println("forkJoin迭代：" + measurePref(ForkJoinSumCalculator::forkJoinSum, 1_000_000L));
    }

    public static <T, R> long measurePref(Function<T, R> f, T input) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            R result = f.apply(input);
            long duration = (System.nanoTime() - start) / 1_000_000;
            //System.out.println("Result:" + result);
            if (duration < fastest) {
                fastest = duration;
            }
        }
        return fastest;
    }
}
