package java_structure_arithmetic;

import java.util.Arrays;

/**
 * 没有记录数据项个数的队列
 * Created by lx on 2016/8/20.
 */
public class NItemQueue {
    private int maxSize;
    private long[] queArr;
    private int front;//队头
    private int rear;//队尾

    public NItemQueue(int maxSize) {
        this.maxSize = maxSize + 1;
        queArr = new long[maxSize];
        front = 0;
        rear = -1;
    }

    /**
     * 插入
     */
    public void insert(long j) throws Exception {
        if(isFull()){
            throw new Exception("队列已满");
        }
        if (rear == maxSize - 1) {
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
        if (front == maxSize){
            front = 0;
        }
        return temp;
    }

    /**
     * 返回队头
     * @return
     */
    public long peekFront() {
        return queArr[front];
    }

    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty() {
        return front + maxSize -1 == rear || front == rear + 1;
    }

    /**
     * 是否满了
     * @return
     */
    public boolean isFull() {
        return front == rear + 2 || front + maxSize - 2 == rear;
    }

    /**
     * 长度
     * @return
     */
    public int size () {
        if (rear >= front) {
            return rear - front + 1;
        } else {
            return (maxSize - front) + (rear + 1);
        }
    }

    @Override
    public String toString() {
        return "Queue{" +
                "queArr=" + Arrays.toString(queArr) +
                '}';
    }
}

class NItemQueueTest {
    public static void main(String[] args) {
        NItemQueue q = new NItemQueue(5);
        try {
            q.insert(10);
            q.insert(20);
            q.insert(30);
            q.insert(40);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //(10, 20 , 30, 40)
//        System.out.println(q);

        q.remove();
        q.remove();
        q.remove();
//        System.out.println(q);
        //remove (10 , 20 , 30)

        try {
            q.insert(50);
            q.insert(60);
            q.insert(70);
            q.insert(80);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //环绕队列

        while (!q.isEmpty()) {
            long n = q.remove();
            System.out.print(n + " ");
        }

    }
}
