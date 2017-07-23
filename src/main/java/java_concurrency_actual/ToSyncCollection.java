package java_concurrency_actual;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 将普通的容器转换成同步的容器
 * Created by Administrator on 2017/5/7 0007.
 */
public class ToSyncCollection {


    public static void main(String[] args) {
        List list = new ArrayList<>();
        List syncList = Collections.synchronizedList(list);

        Map map = new HashMap<>();
        Map syncMap = Collections.synchronizedMap(map);

        List linkList = new LinkedList<>();
        List syncLinkList = Collections.synchronizedList(linkList);

        Vector v = new Vector();
        ConcurrentHashMap cMap = new ConcurrentHashMap();

        ConcurrentLinkedQueue clq = new ConcurrentLinkedQueue();

        int i = 1;
        int j = 1;

        boolean b = (i != (i = j));
        System.out.println(b);

//        while(true);

//        for (int i = 1, j = 1;;) {
//            System.out.println(i + j);
//        }
    }

}
