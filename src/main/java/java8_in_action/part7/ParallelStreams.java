package java8_in_action.part7;

import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @Author lx
 * @Date 2018/4/6 21:20
 */
public class ParallelStreams {

    /**
     * 顺序迭代
     *
     * @param n
     * @return
     */
    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 0; i < n; i++) {
            result += i;
        }
        return result;
    }

    /**
     * 流顺序迭代
     *
     * @param n
     * @return
     */
    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).reduce(Long::sum).get();
    }

    /**
     * 流并行迭代
     *
     * @param n
     * @return
     */
    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(Long::sum).get();
    }

    /**
     * 基本类型顺序
     * @param n
     * @return
     */
    public static long rangeSum(long n) {
        return LongStream.rangeClosed(1, n).reduce(Long::sum).getAsLong();
    }

    /**
     * 基本类型并行
     * @param n
     * @return
     */
    public static long parallelRangeSum(long n) {
        return LongStream.rangeClosed(1, n).parallel().reduce(Long::sum).getAsLong();
    }

    /**
     * 顺序修改共享变量
     * @param n
     * @return
     */
    public static long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).forEach(accumulator::add);
        return accumulator.total;
    }

    /**
     * 并发修改共享变量
     * @param n
     * @return
     */
    public static long sideEffectParallelSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }
}
