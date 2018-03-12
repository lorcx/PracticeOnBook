package java_concurrency_in_pratice.part15;

/**
 * CAS计数器
 * @Author lx
 * @Date 2018/2/13 15:34
 */
public class CasCounter {
    private SimulatedCAS value;

    public int getValue() {
        return value.get();
    }

    public int increment() {
        int v;
        do {
            v = value.get();
        } while (v != value.compareAndSwap(v, v + 1));
        return v;
    }
}
