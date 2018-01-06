package java_concurrency_in_pratice.part4.vehicle_tracker3;

import java_concurrency_in_pratice.anno.ThreadSafe;

/**
 * @Author lx
 * @Date 2018/1/6 18:28
 */
@ThreadSafe
public class SafePoint {
    private int x, y;

    // 防止产生静态条件
    private SafePoint(int[] a) {
        this(a[0], a[1]);
    }

    public SafePoint(SafePoint p) {
        this(p.get());
    }

    public SafePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private synchronized int[] get() {
        return new int[] {x, y};
    }

    public synchronized void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
