package java_concurrency_in_pratice.part4.vehicle_tracker2;

import java_concurrency_in_pratice.anno.ThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Author lx
 * @Date 2018/1/6 17:43
 */
@ThreadSafe
public class DelegatingVehicleTracker {
    private final ConcurrentMap<String, Point> locations;
    private final Map<String, Point> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String, Point> points) {
        this.locations = new ConcurrentHashMap<>(points);
        this.unmodifiableMap = Collections.unmodifiableMap(points);
    }

    // 返回locations的实时数据
//    public Map<String, Point> getLocations() {
//        return unmodifiableMap;
//    }

    // 返回locations的静态浅拷贝
    public Map<String, Point> getLocations() {
        return Collections.unmodifiableMap(new HashMap<>(locations));
    }

    public Point getLocation(String id) {
        return locations.get(id);
    }

    public void setLocations(String id, int x, int y) {
        if (locations.replace(id, new Point(x, y)) == null) {
            throw new IllegalArgumentException("invalid vehicle name : " + id);
        }
    }
}
