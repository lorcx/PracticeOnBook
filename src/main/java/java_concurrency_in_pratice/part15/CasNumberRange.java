package java_concurrency_in_pratice.part15;

import java_concurrency_in_pratice.anno.ThreadSafe;
import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author lx
 * @Date 2018/2/13 15:37
 */
@ThreadSafe
public class CasNumberRange {
    @Immutable // 不可变
    private static class IntPair {
        final int lower;
        final int upper;

        public IntPair(int lower, int upper) {
            this.lower = lower;
            this.upper = upper;
        }
    }

    private final AtomicReference<IntPair> values = new AtomicReference<>();

    public int getLower() {
        return values.get().lower;
    }

    public int getUpper() {
        return values.get().upper;
    }

    public void setLower(int i) {
        while (true) {
            IntPair oldv = values.get();
            if (i > oldv.upper) {
                throw new IllegalArgumentException("Can't set lower to " + i + " > uppper");
            }

            IntPair newv = new IntPair(i, oldv.upper);
            if (values.compareAndSet(oldv, newv)) {
                return;
            }
        }
    }

    public void setUpper(int i) {
        while (true) {
            IntPair oldv = values.get();
            if (i < oldv.lower) {
                throw new IllegalArgumentException("Can't set uppder to " + i + " < lower");
            }
            IntPair newv = new IntPair(oldv.lower, i);
            if (values.compareAndSet(oldv, newv)) {
                return;
            }
        }
    }
}


