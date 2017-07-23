package java_structure_arithmetic;

import java.util.Arrays;

/**
 * 队列
 * Created by lx on 2016/8/20.
 */
public class QueueBfs {
    private final int SIZE = 20;
    private long[] queArr;
    private int front;//队头
    private int rear;//队尾

    public QueueBfs(int maxSize) {
        queArr = new long[SIZE];
        front = 0;
        rear = -1;
    }

    /**
     * 插入
     */
    public void insert(long j) throws Exception {
        if (rear == SIZE - 1) {
            rear = -1;
        }
        queArr[++rear] = j;
    }

    /**
     * 移除队头
     * @return
     */
    public long remove() {
        long temp = queArr[front++];
        if (front == SIZE){
            front = 0;
        }
        return temp;
    }

    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty() {
        return rear + 1 == front || front + SIZE - 1 == rear;
    }

    @Override
    public String toString() {
        return "Queue{" +
                "queArr=" + Arrays.toString(queArr) +
                '}';
    }
}

