package java_concurrency_in_pratice.part4;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 复合操作
 * @Author lx
 * @Date 2018/1/7 15:24
 */
public class VectorFH {
    private static List list = new ArrayList<>();
    public static Object getLast(Vector v) {
        synchronized (list) {
            int lastIndex = list.size() - 1;
            return list.get(lastIndex);
        }
    }

    public static void deleteLast(Vector v) {
        synchronized (list) {
            int lastIndex = list.size() - 1;
            list.remove(lastIndex);
        }
    }
}
