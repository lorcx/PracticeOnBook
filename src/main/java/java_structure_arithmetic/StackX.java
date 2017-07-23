package java_structure_arithmetic;

import java.util.Arrays;

/**
 * 栈实现
 * Created by lx on 2016/8/20.
 */
public class StackX <T>{
    private int maxSize;
    private T[] stackArr;
    private int top;

    public StackX(int maxSize) {
        this.maxSize = maxSize;
        stackArr = (T[])new Object[maxSize];
        top = -1;//没有记录
    }

    /**
     * 入栈
     */
    public void push(T data) {
//        System.out.println("top" + top);
        stackArr[++top] = data;
    }

    /**
     * 出栈
     *
     * @return
     */
    public T pop() {
//        int len = top--;
//        int data = stackArr[len];
//        stackArr[len] = 0;
//        return data;
        return stackArr[top--];
    }

    /**
     * 返回栈顶
     *
     * @return
     */
    public T peek() {
        return stackArr[top];
    }

    /**
     * 是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 是否栈满了
     *
     * @return
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    @Override
    public String toString() {
        return Arrays.toString(stackArr);
    }
}

class StackXTest {

    public static void main(String[] args) {
        StackX<Integer> sx = new StackX(10);
        int i = 0;
        while (!sx.isFull()) {
            sx.push(i++);
        }
        while (!sx.isEmpty()) {
            System.out.println(sx.pop());
        }
        System.out.println(sx);
    }
}
