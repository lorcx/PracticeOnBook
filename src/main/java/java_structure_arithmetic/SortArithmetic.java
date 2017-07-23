package java_structure_arithmetic;

import java.util.Random;

/**
 * 冒泡排序
 * Created by lx on 2016/8/14.
 */
public class SortArithmetic {
    private long[] data;
    private int index;

    public SortArithmetic(int size) {
        data = new long[size];
    }

    /**
     * 插入数据
     *
     * @param item
     */
    public void insert(long item) {
        data[index] = item;
        index++;
    }

    /**
     * 打印结果
     */
    public void display() {
        System.out.println("=======display start========");
        for (long d : data) {
            System.out.println(d + "\t");
        }
        System.out.println("=======display end========");
    }

    /**
     * 交换
     *
     * @param l
     * @param l1 参数是基本类型传递的是值不是引用
     */
    private void swap(int l, int l1) {
        long tmp;
        tmp = data[l];
        data[l] = data[l1];
        data[l1] = tmp;
    }


    /**
     * 冒泡排序 最简单的排序 复杂度 O = N * N
     * 核心思想
     * 外层循环从后往前
     * 内层循环从前往后
     * 如果左边的大于右边的则交换2者
     */
    public void bubbleSort() {
        for (int i = index - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (data[j] > data[j + 1]) {
                    swap(j, j + 1);
                }
            }
        }
        display();
    }

    /**
     * 选择排序 跟冒泡排序类似 交换 O=N  比较O = N * N
     * 对冒牌排序进行了优化
     * 核心思想
     * 第一次将扫描全部把最小的放在左边
     * 第二次扫描全部把除了第一个的最小的放在第一个位置 以此类推\
     * <p/>
     * 因为 每次比较都把最小的放在左边，
     * 所以下一次比较就不用比较上一个了内层 j = i + 1 ,
     * 为了防止数组越界所以外层只要循环到倒数第二个就行了
     * <p/>
     * 内侧循环目的找到最小的下标，然后在交换
     */
    public void selectSort() {
        //记录最小位置
        int min;
        for (int i = 0; i < index - 1; i++) {
            min = i;
            for (int j = i + 1; j < index; j++) {
                if (data[j] < data[min]) {
                    min = j;
                }
            }
            swap(i, min);
        }
        display();
    }

    /**
     * 插入排序
     * 复杂度 O = N *　Ｎ
     * 首先选择一个数据项作为标记，他跟左边的数据每个进行比较，当找到比他小的元素就插入
     * 到这里
     * 外层循环用来移动标记元素
     * 内层循环用来比较并插入
     * 比冒泡快1倍 被选择稍快 算法复杂度适中
     * 局部有序
     * 如果数据是随机的比冒泡和选择排序快 ，如果数据是逆序的 每次都要复制和比较 就没冒泡选择快了
     */
    public void insertSort() {
        int in;
        for (int out = 0; out < index; out++) {
            //被标记的对象
            long tmp = data[out];
            in = out;
            while (in > 0 && data[in - 1] >= tmp) {
                data[in] = data[in - 1];
                in--;
            }
            data[in] = tmp;
        }
        display();
    }

    /**
     * 快速排序 对小数组使用插入排序
     */
    public void insertSort1(int left, int right) {
        int in;
        for (int out = left + 1; out <= right; out++) {
            //被标记的对象
            long tmp = data[out];
            in = out;
            while (in > left && data[in - 1] >= tmp) {
                data[in] = data[in - 1];
                --in;
            }
            data[in] = tmp;
        }
//        display();
    }

    /**
     * 归并排序
     * 比简单排序好：冒泡 选择 插入
     * 比高级排序简单：快速 希尔
     * 用<空间换>时间
     * 复杂度：O = N * logN
     * 核心思想 将2个数组中的元素进行比较 ，将最小的放入C数组中，剩余元素直接复制到C中
     *
     */
    public int[] mergeSort(int[] arrA, int sizeA, int[] arrB, int sizeB) {
        int aDex = 0, bDex = 0, cDex = 0;
        int[] arrC = new int[sizeA + sizeB];
        //循环2个数组 把其中最小的 放入 c 直到其中一个 为空
        while (aDex < sizeA && bDex < sizeB) {
            if (arrA[aDex] < arrB[bDex]) {
                arrC[cDex++] = arrA[aDex++];
            } else {
                arrC[cDex++] = arrB[bDex++];
            }
        }
        // a 还有剩余元素
        while (aDex < sizeA) {
            arrC[cDex++] = arrA[aDex++];
        }
        // b 还有剩余元素
        while (bDex < sizeB) {
            arrC[cDex++] = arrB[bDex++];
        }
        return arrC;
    }

    /**
     * 递归归并排序
     * 只多用一个数组
     */
    public void mergeSort() {
        long[] workspace = new long[index];
        recMergeSort(workspace, 0, index - 1);
    }

    private void recMergeSort(long[] workspace, int lowerBound, int upperBound) {
        if (lowerBound == upperBound) {
            return; // 1
        } else {
            int mid = (lowerBound + upperBound) / 2;
            recMergeSort(workspace, lowerBound, mid);
            recMergeSort(workspace, mid + 1, upperBound);
            merge(workspace, lowerBound, mid + 1, upperBound);
        }
    }

    private void merge(long[] workspace, int lowPtr, int highPtr, int upperBound) {
        int j = 0;
        int lowerBound = lowPtr;
        int mid = highPtr - 1;
        int n = upperBound - lowerBound + 1;
        while (lowPtr <= mid && highPtr <= upperBound) {
            if (data[lowPtr] < data[highPtr]) {
                workspace[j++] = data[lowPtr++];
            } else {
                workspace[j++] = data[highPtr++];
            }
        }
        while (lowPtr <= mid) {
            workspace[j++] = data[lowPtr++];
        }
        while (highPtr <= upperBound) {
            workspace[j++] = data[highPtr++];
        }
        for (j = 0; j < n; j++) {
            data[lowerBound + j] = workspace[j];
        }
    }


    /**
     * 希尔排序
     */
    public void shellSort() {
        int outer;int inner;long tmp;
        int h = 1;
        //先找到增量 间隔序列
        while (h <= index / 3) {
            h = 3 * h + 1;
        }
        while (h > 0) {
            for (outer = h; outer < index; outer++) {
               tmp = data[outer];
               inner = outer;
               while (inner > h - 1 && data[inner - h] >= tmp) {
                  data[inner] = data[inner - h];
                  inner -= h;
               }
               data[inner] = tmp;
            }
            h = (h - 1) / 3;
        }
    }

    /**
     * 划分思想 O = N
     * @param left
     * @param right
     * @param pivot
     * @return
     */
    public int partitionIt(int left, int right, long pivot ) {
        int leftPtr = left - 1;
        int rightPtr = right;
        while (true) {
            //找到最大值
            // 前一个 判断防止数组下标越界
            while (leftPtr < right && data[++leftPtr] < pivot);
            //找到最小值
            // 前一个 判断防止数组下标越界
            while (rightPtr > left && data[--rightPtr] > pivot);
            if (leftPtr >= rightPtr) {
                break;
            } else {
                swap(leftPtr, rightPtr);
            }
        }
        swap(leftPtr, right);
        return leftPtr;
    }

    /**
     * 快速排序
     * 划分的思想
     */
    public void quickSort(int left, int right) {
        if (right - left <= 0) {
            return;//返回上一次递归
        }
        long pivot = data[right];//枢纽
        int dex = partitionIt(left, right, pivot);
        //对枢纽两边的子数组进行排序
        quickSort(left, dex - 1);
        quickSort(dex + 1, right);
    }

    /**
     * 快速排序改进版 提高速度 防止降低到O = N*N
     * 使用3个数的中项作为中枢
     * @param left
     * @param right
     */
    public void quickSort1(int left, int right) {
        int size = right - left + 1;
        if (size <= 3) {
            manualSort(left, right);
        } else {
            long median = medianOf3(left, right);
            int partition = partitionIt1(left, right, median);
            quickSort1(left, partition - 1);
            quickSort1(partition + 1, right);
        }
    }

    /**
     * 对3个以上的进行排序
     * 找到 子数组的中枢
     * 从 0 和 最后一个之间找到 中枢 进行排序
     * @param left
     * @param right
     * @return
     */
    private long medianOf3(int left, int right) {
        int center = (left + right) / 2;
        if (data[left] > data[center]) {
            swap(left, center);
        }
        if (data[left] > data[right]) {
            swap(left, right);
        }
        if (data[center] > data[right]) {
            swap(center, right);
        }
        swap(center, right - 1);
        return data[right - 1];
    }

    /**
     * 对只有3个的数据项进行排序
     * @param left
     * @param right
     */
    private void manualSort(int left, int right) {
        int size = right - left + 1;
        if (size <= 1) {//退出
            return;
        }
        if (size == 2) {//交换
            if (data[left] > data[right]) {
                swap(left, right);
            }
            return;
        } else {//排序
            if (data[left] > data[right - 1]) {
                swap(left, right - 1);
            }
            if (data[left] > data[right]) {
                swap(left, right);
            }
            if (data[right - 1] > data[right]) {
                swap(right - 1, right);
            }
        }

    }

    public int partitionIt1(int left, int right, long pivot ) {
        int leftPtr = left;
        int rightPtr = right - 1;
        while (true) {
            //找到最大值
            // 前一个 判断防止数组下标越界
            while (data[++leftPtr] < pivot);
            //找到最小值
            // 前一个 判断防止数组下标越界
            while (data[--rightPtr] > pivot);
            if (leftPtr >= rightPtr) {
                break;
            } else {
                swap(leftPtr, rightPtr);
            }
        }
        swap(leftPtr, right - 1);
        return leftPtr;
    }

    /**
     * 快速排序改进版 提高速度 防止降低到O = N*N
     * 对于小数组使用使用插入排序
     * @param left
     * @param right
     */
    public void quickSort2(int left, int right) {
        int size = right - left + 1;
        if (size < 10) {//小于10个就用插入排序
            insertSort1(left, right);
        } else {
            long median = medianOf3(left, right);
            int partition = partitionIt1(left, right, median);
            quickSort1(left, partition - 1);
            quickSort1(partition + 1, right);
        }
    }


    public int size() {
        return data.length;
    }
}

//测试
class BubbleSortTest {
    public static void main(String[] args) {
        SortArithmetic sa = new SortArithmetic(10);
//        sa.insert(100);
//        sa.insert(324);
//        sa.insert(546);
//        sa.insert(341);
//        sa.insert(879);
//        sa.insert(412);
//        sa.insert(648);
//        sa.insert(999);
//        sa.insert(762);
//        sa.insert(395);
////        sa.bubbleSort();
////        sa.selectSort();
//        sa.insertSort();

        //普通归并
//        int[] arrA = {23,47,81,95};
//        int[] arrB = {7,14,39,55,62,74};
//        int[] arrC = sa.mergeSort(arrA, arrA.length, arrB, arrB.length);
//        System.out.println(Arrays.toString(arrC));

        //递归归并
//        sa.mergeSort();
//        sa.display();

        Random random = new Random(47);
//        //希尔排序
//        for (int i = 0; i < 10; i++) {
//            //math random生成 0.0 - 1.0 之间 所以要先乘 在 类型转换
//            long n = (int) (Math.random() * 99);
//            sa.insert(n);
//        }
//        sa.display();
//        sa.shellSort();
//        sa.display();

        for (int i = 0; i < 5; i++) {
            long n = (int)(Math.random() * 199);
            sa.insert(n);
        }
        sa.display();
//        long pivot = 99;
//        System.out.print("Pivot is " + pivot);
        int size = sa.size();
//        int partDex = sa.partitionIt(0, size - 1,pivot);
//        sa.display();

        sa.quickSort2(0, size - 1);

        sa.display();
    }
}