package java_structure_arithmetic;

/**
 * 双端链表
 * Created by dell on 2016/8/24.
 */
public class LinkD {
    public int iData;
    public LinkD next;//下一个节点
    public LinkD prev;//上一个节点

    public LinkD(int iData) {
        this.iData = iData;
    }

    @Override
    public String toString() {
        return "LinkD{" +
                "iData=" + iData +
                '}';
    }
}
