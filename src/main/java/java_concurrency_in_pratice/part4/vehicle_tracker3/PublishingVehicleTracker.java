package java_concurrency_in_pratice.part4.vehicle_tracker3;

import java_concurrency_in_pratice.anno.ThreadSafe;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 安全发布底层状态的车辆追踪器
 * 如果在车辆的有效位置上施加任何约束，那么就不是线程安全的
 * @Author lx
 * @Date 2018/1/6 18:33
 */
@ThreadSafe
public class PublishingVehicleTracker {
    private final Map<String, SafePoint> locations;
    private final Map<String, SafePoint> unmodifiableMap;

    public PublishingVehicleTracker(Map<String, SafePoint> locations) {
        this.locations = new ConcurrentHashMap<>(locations);
        this.unmodifiableMap = Collections.unmodifiableMap(this.locations);
    }

    public Map<String, SafePoint> getLocations() {
        return unmodifiableMap;
    }

    public SafePoint getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if (!locations.containsKey(id)) {
            throw new IllegalArgumentException("invalid vehicle name " + id);
        }
        locations.get(id).set(x, y);
    }
}
