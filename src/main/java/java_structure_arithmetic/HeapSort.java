package java_structure_arithmetic;

import java.io.IOException;
import java.util.Scanner;

/**
 * 堆排序
 * 比快速排序稍慢
 * 把无序数据放入堆，每次调用remove获得
 * O = N * LOG N
 * 快速排序对初始化数据敏感 可能会降到N * N
 * Created by lx on 2016/10/1.
 */
public class HeapSort {
    private HeapNode[] heapArray;
    private int maxSize;
    private int currentSize;

    public HeapSort(int maxSize) {
        this.maxSize = maxSize;
        currentSize = 0;
        heapArray = new HeapNode[maxSize];
    }

    /**
     * 删除
     * @return
     */
    public HeapNode remove(){
        HeapNode root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }

    protected void trickleDown(int index) {
        int largeChild;
        HeapNode top = heapArray[index];
        while (index < currentSize / 2) {
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;
            if (rightChild < currentSize && heapArray[leftChild].getKey() < heapArray[rightChild].getKey()) {
                largeChild = rightChild;
            } else {
                largeChild = leftChild;
            }
            if (top.getKey() >= heapArray[largeChild].getKey()) {
                break;
            }
            heapArray[index] = heapArray[largeChild];
            index = largeChild;
        }
        heapArray[index] = top;
    }


    /**
     * 显示
     */
    public void displayHeap() {
        System.out.print("heapArray:");
        for (int m = 0; m < currentSize; m++) {
            if (heapArray[m] != null) {
                System.out.println(heapArray[m].getKey() + " ");
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
            System.out.print(heapArray[j].getKey());
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

    public void displayArray() {
        for (int j = 0; j < maxSize; j++) {
            System.out.println(heapArray[j].getKey() + " ");
        }
        System.out.println(" ");
    }

    public void incrementSize() {
        currentSize++;
    }

    public void insertAt(int index, HeapNode newNode) {
        heapArray[index] = newNode;
    }
}

class HeapSortApp{

    public static void main(String[] args) throws IOException {
        int size,j;
        System.out.println("请输入数字");
        size = getInt();
        HeapSort hs = new HeapSort(size);
        for (j = 0; j < size; j++) {
            int random = (int) (Math.random() * 100);
            HeapNode newNode = new HeapNode(random);
            hs.insertAt(j,newNode);
            hs.incrementSize();
        }
        System.out.println("Random: ");
        hs.displayArray();
        // 对 N / 2个数据项用向下筛选 ，而不做N次插入 ，速度会运行的更快
        for (j = size / 2 -1; j >= 0; j--) {
            hs.trickleDown(j); // 将数组变成堆
        }

        System.out.println("heap : ");
        hs.displayArray();
        hs.displayHeap();
        for (j = size - 1; j >= 0;j--) {
            HeapNode biggestNode = hs.remove();
            hs.insertAt(j,biggestNode);// 从后往前插入
        }
        System.out.println("Sorted:");
        hs.displayArray();

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
