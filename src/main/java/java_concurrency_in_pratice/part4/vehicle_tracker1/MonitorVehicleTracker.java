package java_concurrency_in_pratice.part4.vehicle_tracker1;

import java_concurrency_in_pratice.anno.ThreadSafe;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 基于监视器模式的车辆追踪
 *
 * 使用deepcopy进行同步，在数据量大时性能下降严重
 *
 * 因为返回的是copy，所以可能导致不一致的车辆位置
 * @Author lx
 * @Date 2018/1/6 17:16
 */
@ThreadSafe
public class MonitorVehicleTracker {
    private final Map<String, MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = locations;
    }

    public synchronized Map<String, MutablePoint> getLocations() {
        return deepCopy(locations);
    }

    public synchronized void setLocations(String id, int x, int y) {
        MutablePoint loc = locations.get(id);
        if (loc == null) {
            throw new IllegalArgumentException("No such ID:" + id);
        }
        loc.x = x;
        loc.y = y;
    }

    private static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> m) {
        Map<String, MutablePoint> resultMap = new HashMap<>();
        for (String id : m.keySet()) {
            resultMap.put(id, new MutablePoint(m.get(id)));
        }
        // 返回一个不可修改的map，如果修改则会抛出异常
        return Collections.unmodifiableMap(resultMap);
    }
}


