package java_structure_arithmetic;

/**
 * 最短路径算法
 * 记录父节点和距离
 * Created by lx on 2016/10/5.
 */
public class DistPar {
    public int distance;// 距离
    public int parentVert;// 父顶点

    public DistPar(int distance, int parentVert) {
        this.distance = distance;
        this.parentVert = parentVert;
    }
}
