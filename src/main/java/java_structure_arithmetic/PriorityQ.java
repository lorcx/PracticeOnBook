package java_structure_arithmetic;

/**
 * 优先级队列 通过数组实现
 * 用于实现有向图最小生成树算法
 * Created by lx on 2016/10/5.
 */
public class PriorityQ {
    private final int SIZE = 20;
    private Edge[]  queArray;
    private int size;

    public PriorityQ() {
        queArray = new Edge[SIZE];
        size = 0;
    }

    /**
     * 插入
     */
    public void insert(Edge item) {
        int j;
        // 找到插入位置的索引
        for (j = 0; j < size; j++) {
            if (item.distance >= queArray[j].distance) {
                break;
            }
        }
        // 将插入位置后的数据项，向后移动
        for (int k = size - 1;k >= j; k--) {
            queArray[k + 1] = queArray[k];
        }
        queArray[j] = item;
        size++;
    }

    /**
     * 删除最小边
     * @return
     */
    public Edge removeMin() {
        return queArray[--size];
    }

    /**
     * 删除第n个元素
     * @param n
     */
    public void removeN(int n) {
        // 覆盖
        for (int j = n; j < size - 1;j++) {
            queArray[j] = queArray[j + 1];
        }
        size--;
    }

    /**
     * 获取最小边
     * @return
     */
    public Edge peekMin () {
        return queArray[size - 1];
    }

    /**
     * 队列大小
     * @return
     */
    public int size () {
        return size;
    }

    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 弹出第n个元素
     * @param n
     * @return
     */
    public Edge peekN(int n) {
        return queArray[n];
    }

    /**
     * 查找
     * @param findDex
     * @return
     */
    public int find(int findDex) {
        for (int j = 0; j < size; j++) {
            if(queArray[j].destVert == findDex) {
                return j;
            }
        }
        return -1;
    }
}
