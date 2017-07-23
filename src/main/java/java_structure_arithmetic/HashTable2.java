package java_structure_arithmetic;

/**
 * hash 表 （链表地址法） 没有实现去重 插入key 不能重复
 * Created by lx on 2016/9/17.
 */
public class HashTable2 {
    private SortedList[] hashArray; // 容器
    private int arraySize; // 数组大小

    public HashTable2(int arraySize) {
        this.arraySize = arraySize;
        this.hashArray = new SortedList[arraySize];
        for (int j = 0; j < arraySize; j++) {
            hashArray[j] = new SortedList();
        }
    }

    /**
     * 显示hash表
     */
    public void displayTable() {
        for (int i = 0; i < arraySize; i++) {
            if (hashArray[i] != null) {
                System.out.print(i + ". ");
                hashArray[i].displayList();
            }
        }
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
     * 插入
     *
     * @param item
     */
    public void insert(Link item) {
        int key = item.iData;
        int hashVal = hashFunc(key);
        hashArray[hashVal].insert(item); // 插入到有序链表中
    }

    /**
     * 查找
     *
     * @return
     */
    public Link find(int key) {
        int hashVal = hashFunc(key);
        Link theLink = hashArray[hashVal].find(key);
        return theLink;
    }

    /**
     * 删除
     *
     * @return
     */
    public void delete(int key) {
        int hashVal = hashFunc(key);
        hashArray[hashVal].delete(key);
    }

}
