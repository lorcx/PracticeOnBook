package java_structure_arithmetic;

import java.io.IOException;
import java.util.Scanner;

/**
 * 堆 用数组表示二叉树
 * 用堆实现优先级队列
 * Created by dell on 2016/9/29.
 */
public class Heap {
    Node[] heapArray;
    private int currentSize;// 当前节点大小
    private int maxSize;// 数组大小

    public Heap(int maxSize) {
        this.maxSize = maxSize;
        currentSize = 0;
        heapArray = new Node[maxSize];
    }

    public boolean isEmpty() {
        return heapArray.length == 0;
    }

    /**
     * 插入
     * <p/>
     * 先插入到最后一个节点 ，然后向上遍历
     * 直到找到父节点比他大 子节点比他小
     *
     * @param key
     * @return
     */
    public boolean insert(int key) {
        // 检查是否满了
        if (currentSize == maxSize) {
            return false;
        }
        Node newNode = new Node();
        newNode.iData = key;
        heapArray[currentSize] = newNode;// 插入到最后一个节点
        trickleUp(currentSize++);
        return true;
    }

    /**
     * 向上过滤
     * 用 复制代替 交换
     *
     * @param index
     */
    private void trickleUp(int index) {
        int parent = (index - 1) / 2;// 公式
        Node bottom = heapArray[index];// 复制到一个临时变量
        while (index > 0 && heapArray[parent].iData < bottom.iData) {
            heapArray[index] = heapArray[parent];// 移动节点数据
            index = parent;// 移动节点索引
            parent = (parent - 1) / 2;// 更新父节点
        }
        heapArray[index] = bottom;
    }

    /**
     * 删除
     *
     * @return 返回被删除的节点
     */
    public Node delete() {
        Node root = heapArray[0];// 保存根节点
        heapArray[0] = heapArray[--currentSize];// root <- last
        tickleDown(0);
        return root;
    }

    /**
     * 向下过滤,要找到尽可能大的节点
     * 将根节点删除后，把尽可能大的子节点复制到根节点
     *
     * @param index least 最小
     */
    private void tickleDown(int index) {
        int largerChild;
        Node top = heapArray[index];// 保存根节点
        while (index < currentSize / 2) {// 循环到最小的一个子节点
            int leftChild = 2 * index + 1; //左子节点索引 数组存储二叉树的公式
            int rightChild = leftChild + 1; // 右子节点的索引
            // 区分出左子节点和右子节点那个大
            if (rightChild < currentSize && heapArray[leftChild].iData < heapArray[rightChild].iData) {
                largerChild = rightChild;
            } else {
                largerChild = leftChild;
            }
            if (top.iData >= heapArray[largerChild].iData) {
                break;// 根节点 > 最大的子节点
            }
            heapArray[index] = heapArray[largerChild];// 复制到根节点
            index = largerChild;
        }
        heapArray[index] = top;// 恢复
    }

    /**
     * 改变节点的值
     * 并且调整优先级
     *
     * @return
     * @param: index
     * @param: newValue
     */
    public boolean change(int index, int newValue) {
        // 检查是否合法
        if (index < 0 || index >= currentSize) {
            return false;
        }
        int oldValue = heapArray[index].iData;// 保存旧的值
        heapArray[index].iData = newValue;// 放入新的值
        if (oldValue < newValue) {
            trickleUp(index);
        } else {
            tickleDown(index);
        }
        return true;
    }

    /**
     * 显示
     */
    public void displayHeap() {
        System.out.print("heapArray:");
        for (int m = 0; m < currentSize; m++) {
            if (heapArray[m] != null) {
                System.out.println(heapArray[m].iData + " ");
            } else {
                System.out.println("--");
            }
        }
        System.out.println();
        // heap 格式化
        int nBlanks = 32;// 空格个数
        int itemsPerRow = 1;
        int column = 0 ;
        int j = 0;
        String dots = ".....................................";
        System.out.println(dots + dots);
        while (currentSize > 0) {
            if (column == 0) {//
                for (int k = 0; k < nBlanks;k++) {
                    System.out.print(" ");
                }
            }
            System.out.print(heapArray[j].iData);
            if (++j == currentSize) {
                break;
            }
            if (++column == itemsPerRow) {
                nBlanks /= 2;
                itemsPerRow *= 2;
                column = 0;
                System.out.println("");
            } else {
                for (int k = 0; k < nBlanks * 2 - 2;k++) {
                    System.out.print(" ");
                }
            }
        }
        System.out.println("\n" + dots + dots);
    }
}

class HeapApp {

    public static void main(String[] args) throws IOException {
        int value ,value2;
        Heap theHeap = new Heap(31);
        boolean success;
        theHeap.insert(70);
        theHeap.insert(40);
        theHeap.insert(50);
        theHeap.insert(20);
        theHeap.insert(60);
        theHeap.insert(100);
        theHeap.insert(80);
        theHeap.insert(30);
        theHeap.insert(10);
        theHeap.insert(90);
        while (true) {
            System.out.println("请输入下列单词第一个字符：");
            System.out.println("show, insert, remove, change:");
            int choice = getChar();
            switch (choice) {
                case 's' :
                    theHeap.displayHeap();
                    break;
                case 'i' :
                    System.out.println("请输入插入的数据：");
                    value = getInt();
                    success = theHeap.insert(value);
                    if (!success) {
                        System.out.println("不能插入;堆为空");
                    }
                    break;
                case 'r' :
                    if (!theHeap.isEmpty()) {
                        theHeap.delete();
                    } else {
                        System.out.println("不能删除；堆为空");
                    }
                    break;
                case 'c' :
                    System.out.println("请输入要改变的值得索引：");
                    value = getInt();
                    System.out.println("请输入一个新的值");
                    value2 = getInt();
                    success = theHeap.change(value,value2);
                    if (!success) {
                        System.out.println("无效的索引");
                    }
                    break;
                default:
                    System.out.println("无效的参数");
            }
        }
    }

    /**
     * 获取用户台输入的字符串
     * @return
     * @throws java.io.IOException
     */
    public static String getString() throws IOException {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        return s;
    }

    /**
     * 获取用户台输入的字符
     * @return
     * @throws IOException
     */
    public static char getChar() throws IOException {
        return getString().charAt(0);
    }

    /**
     * 获取用户台输入的字符串
     * @return
     * @throws IOException
     */
    public static int getInt() throws IOException {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
}
