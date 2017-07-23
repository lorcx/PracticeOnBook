package java_structure_arithmetic;

/**
 * hash 表 当hash化冲突时 线性查找法
 * Created by lx on 2016/9/17.
 */
public class HashTable {
    private DataItem[] hashArray; // 容器
    private int arraySize; // 数组大小
    private DataItem nonItem;// 删除的

    public HashTable(int arraySize) {
        this.arraySize = arraySize;
        this.hashArray = new DataItem[arraySize];
        this.nonItem = new DataItem(-1);
    }

    /**
     * 显示hash表
     */
    public void displayTable() {
        System.out.println("Table:");
        for (int i = 0; i < arraySize; i++) {
            if (hashArray[i] != null) {
                System.out.print(hashArray[i].getKey() + " ");
            } else {
                System.out.print("** ");
            }
        }
        System.out.println();
    }

    /**
     * hash 化 查找原始数组位置
     * 压缩到数组大小范围内
     *
     * @return
     */
    private int hashFunc(int key) {
        return key % arraySize;
    }

    /**
     * 计算步长
     *
     * @return
     */
    private int hashFunc2(int key) {
        // 这里的数字必须是质数，否则会死循环
        return 5 - key % 5;
    }

    /**
     * 插入
     *
     * @param item
     */
    public void insert(DataItem item) {
        int hashVal = hashFunc(item.getKey());
        // 插入到空的并且没有被删除的位置上
        while (hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1) {
            ++hashVal;
            hashVal %= arraySize; // 重新计算hash值
        }
        hashArray[hashVal] = item;
    }

    /**
     * 插入 （再hash法）
     */
    public void insert2(int key, DataItem item) {
        int hashVal = hashFunc(key);
        int stepSize = hashFunc2(key);
        // 找hash后表面的 插入到空的并且没有被删除的位置上
        while (hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1) {
            hashVal += stepSize;// 每一次不再加一而是加上步长
            hashVal %= arraySize; // 重新计算hash值
        }
        hashArray[hashVal] = item;
    }


    /**
     * 查找
     *
     * @return
     */
    public DataItem find(int key) {
        int hashVal = hashFunc(key);
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].getKey() == key) {
                return hashArray[hashVal];
            }
            ++hashVal;
            hashVal %= arraySize;
        }
        return null;
    }


    /**
     * 查找(再hash法)
     *
     * @return
     */
    public DataItem find2(int key) {
        int hashVal = hashFunc(key);
        int stepSize = hashFunc2(key);
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].getKey() == key) {
                return hashArray[hashVal];
            }
            hashVal += stepSize;// 每一次不再加一而是加上步长
            hashVal %= arraySize;
        }
        return null;
    }

    /**
     * 删除
     *
     * @return
     */
    public DataItem delete(int key) {
        int hashVal = hashFunc(key);
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].getKey() == key) {
                DataItem item = hashArray[hashVal];
                hashArray[hashVal] = nonItem; // 置 -1
                return item;
            }
            ++hashVal;
            hashVal %= arraySize;
        }
        return null;
    }

    /**
     * 删除(再hash法)
     *
     * @return
     */
    public DataItem delete2(int key) {
        int hashVal = hashFunc(key);
        int stepSize = hashFunc2(key);
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].getKey() == key) {
                DataItem item = hashArray[hashVal];
                hashArray[hashVal] = nonItem; // 置 -1
                return item;
            }
            hashVal += stepSize;// 每次增加步长
            hashVal %= arraySize;// 再次计算hash
        }
        return null;
    }
}
