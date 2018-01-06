package java_concurrency_in_pratice.part4;

import java_concurrency_in_pratice.anno.NoThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author lx
 * @Date 2018/1/6 18:10
 */
@NoThreadSafe
public class NumberRange {
    // 不变性条件 lower <= uppder
    private final AtomicInteger lower = new AtomicInteger(0);
    private final AtomicInteger upper = new AtomicInteger(0);

    public void setLower(int i) {
        // 不安全的“先检查后执行”
        if (i > upper.get()) {
            throw new IllegalArgumentException("can't set lower to " + i + " > uppder");
        }
        lower.set(i);
    }

    public void setUpper(int i) {
        // 不安全的“先检查后执行”
        if (i < lower.get()) {
            throw new IllegalArgumentException("can't set upper to " + i + " < lower");
        }
        upper.set(i);
    }

    public boolean isInRange(int i) {
        return (i >= lower.get()) && (i < upper.get());
    }
}
