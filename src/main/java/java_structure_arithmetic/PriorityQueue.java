package java_structure_arithmetic;

import java.util.Arrays;

/**
 * 优先级队列 按照最小元素排列
 * Created by lx on 2016/8/20.
 */
public class PriorityQueue {
    private int maxSize;
    private long[] queArr;
    private int nItems;

    public PriorityQueue(int ms) {
        this.maxSize = ms;
        queArr = new long[maxSize];
        nItems = 0;
    }

    public void insert(long data) {
        int i;
        if (nItems == 0) {//没插入呢
            queArr[nItems++] = data;
        } else {
            for (i = nItems - 1; i >= 0; i--) {
                if (data > queArr[i]) {
                    queArr[i + 1] = queArr[i];
                } else {
                    break;
                }
            }
            queArr[i + 1] = data;
            nItems++;
        }
    }

    public long remove() {
        return queArr[nItems--];
    }

    public long peekMin() {
        return queArr[nItems - 1];
    }

    public boolean isEmpty() {
        return nItems == 0;
    }

    public boolean isFull() {
        return nItems == maxSize;
    }

    @Override
    public String toString() {
        return "PriorityQueue{" +
                "queArr=" + Arrays.toString(queArr) +
                '}';
    }
}

class PriorityTest {
    public static void main(String[] args) {
        PriorityQueue pq = new PriorityQueue(5);

        pq.insert(30);
        pq.insert(20);
        pq.insert(40);
        pq.insert(10);

        System.out.println(pq);

//        pq.remove();
//        pq.remove();

        System.out.println(pq.peekMin());
    }
}