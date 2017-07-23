package effective_java;

import java.util.Comparator;

/**
 * 引用类型和包装类型
 * Created by dell on 2016/6/2.
 */
public class WarpType {
    Comparator<Integer> naturalOrder = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            int first = o1;
            int last = o2;
            return first < last ? -1 : (first == last ? 0 : 1);
        }
    };

//    包装类 是对象 == 比较不正确
//    Comparator<Integer> naturalOrder = new Comparator<Integer>() {
//        @Override
//        public int compare(Integer first, Integer last) {
//            return first < last ? -1 : (first == last ? 0 : 1);
//        }
//    };

    public static void main(String[] args) {
        WarpType w = new WarpType();
        System.out.println(w.naturalOrder.compare(new Integer(42),new Integer(42)));
    }
}
