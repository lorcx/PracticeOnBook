package java_concurrency_in_pratice.part3;

import jdk.nashorn.internal.ir.annotations.Immutable;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * 发布不可变对象
 * @Author lx
 * @Date 2018/1/5 21:21
 */
@Immutable
public class OneValueCache {
    private final BigInteger lastNumber;
    private final BigInteger[] lastFactors;

    public OneValueCache(BigInteger i, BigInteger[] factors) {
        lastNumber = i;
        // 如果没有使用Arrays.copyOf那么就不是不可变的
        lastFactors = Arrays.copyOf(factors, factors.length);
    }

    public BigInteger[] getFactors(BigInteger i) {
        if (lastNumber == null || !lastNumber.equals(i)) {
            return null;
        }
        return Arrays.copyOf(lastFactors, lastFactors.length);
    }


}
