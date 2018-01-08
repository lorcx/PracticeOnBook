import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: lx
 * @Date: Created in 2018/1/8 0008
 */
public class TestMap {
    public static void main(String[] args) {
        Map map = new ConcurrentHashMap();
        Object a = map.putIfAbsent("a", 1);
        System.out.println(a);
        Object a1 = map.putIfAbsent("a", 1);
        System.out.println(a1);
    }
}
