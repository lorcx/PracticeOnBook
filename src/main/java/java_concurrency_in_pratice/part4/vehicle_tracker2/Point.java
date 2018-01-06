package java_concurrency_in_pratice.part4.vehicle_tracker2;

import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 * @Author lx
 * @Date 2018/1/6 17:43
 */
@Immutable
public class Point {
    public final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
