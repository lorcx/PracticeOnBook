package java_structure_arithmetic;

/**
 * 链表
 * Created by lx on 2016/8/21.
 */
public class Link {
    public int iData;
    public double dData;
    public Link next;

    public Link(int iData,double dData) {
        this.iData = iData;
        this.dData = dData;
    }

    /**
     * 显示链表
     * @return
     */
    public String displayLink() {
        return "{ "+ iData + ", " + dData +" }";
    }

    /**
     * 显示链表
     * @return
     */
    public void displayLink1() {
        System.out.println("{ "+ iData + ", " + dData +" }");
    }

    @Override
    public String toString() {
        return "Link{" +
                "iData=" + iData +
                ", dData=" + dData +
                '}';
    }
}
