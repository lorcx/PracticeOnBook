package java_structure_arithmetic;

/**
 * 树的节点表示
 * Created by lx on 2016/9/10.
 */
public class Node {
    int iData; // key
    double dData;// 存储数据
    Node leftNode; // 左节点
    Node rightNode;// 右节点

    @Override
    public String toString() {
        return "{" +
                "iData=" + iData +
                ", dData=" + dData +
                '}';
    }
}
