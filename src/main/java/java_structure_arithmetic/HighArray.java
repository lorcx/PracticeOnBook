package java_structure_arithmetic;

import java.util.Arrays;

/**
 * 一个简单的基于数组的容器
 * Created by lx on 2016/8/14.
 */
public class HighArray {
    private long[] data;
    private int index;

    public HighArray(int max) {
        data = new long[max];
        index = 0;
    }


    /**
     * 插入
     */
    public void insert(long item) {
        data[index] = item;
        index++;
    }

    /**
     * 插入并排序
     * @param item
     */
    public void orderInsert(long item){
        int i = 0;
        for (; i < index; i++) {
            if(data[i] > item) {
               break;
            }
        }
        for (int k = index,j = i; k > j; k--) {
            data[k] = data[k - 1];
        }
        data[i] = item;
        index++;
    }

    /**
     * 删除
     */
    public void delete(long item) {
        //线性查找
        int i = 0;
        for (; i < index; i++) {
            if (data[i] == item) {
                break;
            }
        }
        if (i < index) {
            for (int j = i; j < index; j++) {
                if (j < data.length - 1) {
                    data[j] = data[j + 1];
                } else {
                    data[j] = 0;
                }
            }
            index--;
        }
    }

    /**
     * 快速删除 需要排序
     */
    public void quickDelete(long item) {
        //二分查找
        int i = binarySearch(item);
        if(i == -1) {
            return;
        }
        if (i < index) {
            for (int j = i; j < index; j++) {
                if (j < data.length - 1) {
                    data[j] = data[j + 1];
                } else {
                    data[j] = 0;
                }
            }
            index--;
        }
    }

    /**
     * 线性查找
     */
    public void find(long searchKey) {
        for (long nElems : data) {
            if (nElems == searchKey) {
                System.out.println("找到了 ： " + nElems);
                return;
            }
        }
        System.out.println("没有找到");
    }

    /**
     * 二分查找
     * @param searchKey
     */
    public void findRe(long searchKey) {
        int result = binarySeachRe(searchKey, 0, index - 1);
        System.out.println("位置：" + result);
    }

    /**
     * 二分查找
     * 必须要先排序
     * @return index
     */
    public int binarySearch(long searchKey) {
        //最小范围
        int lowerBound = 0;
        //最大范围
        int upperBound = index;
        //记录查找次数
        int i = 0;
        //当前位置
        int curIn;
        while (true) {
            i++;
            curIn = (lowerBound + upperBound) / 2;
            if (data[curIn] == searchKey) {
                System.out.println("找到了 ：" + data[curIn]);
                break;
            } else if (lowerBound > upperBound) {
                //如果这个值不存在 每次upperbound都会减小 最终 lower > upper
                System.out.println("没找到");
                curIn = -1;
                break;
            } else {
                if (data[curIn] < searchKey) {
                    lowerBound = curIn + 1;
                } else {
                    upperBound = curIn - 1;
                }
            }
        }
        System.out.println("查找了" + i + "次");
        return curIn;
    }

    /**
     * 递归二分查找法
     * @return
     */
    public int binarySeachRe(long key, int lowerBound, int upperBound) {
        int curIn = (lowerBound + upperBound) / 2;
        if (data[curIn] == key) {
            return curIn;
        } else if (lowerBound > upperBound) {
            return -1;//没有找到
        } else {
            if (data[curIn] < key) {
                return binarySeachRe(key, curIn + 1, upperBound);
            } else {
                return binarySeachRe(key, lowerBound, curIn - 1);
            }
        }
    }


    /**
     * 显示
     */
    public void display() {
        System.out.println("============data begin=============");
        for (long aData : data) {
            System.out.println(aData + "\t");
        }
        System.out.println("============data end=============");
    }

    /**
     * 排序
     */
    public void sort() {
        Arrays.sort(data);
    }
}

class HighArrayApp {
    public static void main(String[] args) {
        HighArray ha = new HighArray(10);
//        ha.display();
//        ha.insert(100);
//        ha.insert(5789);
//        ha.insert(567);
//        ha.insert(4443);
//        ha.insert(678);
//        ha.insert(452);
//        ha.insert(321);
//        ha.insert(123);
//        ha.insert(567);
//        ha.insert(4545);
//        ha.display();
//        ha.sort();
//        ha.delete(4443);
//        ha.display();
//        ha.find(452);
//        ha.find(4443);

//        System.out.println("-----------二分查找法----------");
//        ha.sort();
//        ha.display();
//        ha.binarySearch(4545);
//        ha.binarySearch(567);
//        ha.binarySearch(123);
//        ha.binarySearch(1111);
        System.out.println("-----------有序插入--------------");
        ha.orderInsert(100);
        ha.orderInsert(5789);
        ha.orderInsert(567);
        ha.orderInsert(4443);
        ha.orderInsert(678);
        ha.orderInsert(452);
        ha.orderInsert(321);
        ha.orderInsert(123);
        ha.orderInsert(952);
        ha.orderInsert(4545);
        ha.display();
        ha.quickDelete(4443);
        ha.display();


        ha.findRe(123);
    }
}
