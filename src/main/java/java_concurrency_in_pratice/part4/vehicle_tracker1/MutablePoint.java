package java_concurrency_in_pratice.part4.vehicle_tracker1;

import java_concurrency_in_pratice.anno.NoThreadSafe;

/**
 * 不建议这么做
 * @Author lx
 * @Date 2018/1/6 17:16
 */
@NoThreadSafe
public class MutablePoint {
    public int x, y;

    public MutablePoint() {
        this.x = 0;
        this.y = 0;
    }

    public MutablePoint(MutablePoint p) {
        this.x = p.x;
        this.y = p.y;
    }
}
