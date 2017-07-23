package java_structure_arithmetic;

/**
 * 有向图 边
 * Created by lx on 2016/10/5.
 */
public class Edge {
    public int srcVert;// 边的开始索引
    public int destVert;// 边的结束索引
    public int distance;// 边的距离

    public Edge(int srcVert, int destVert, int distance) {
        this.srcVert = srcVert;
        this.destVert = destVert;
        this.distance = distance;
    }
}
