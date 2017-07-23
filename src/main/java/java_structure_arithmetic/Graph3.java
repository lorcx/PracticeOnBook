package java_structure_arithmetic;

/**
 * 最短路径
 * Created by lx on 2016/10/5.
 */
public class Graph3 {
    private final int INFINITY = 100000;
    private Vertex2[] vertexList;
    private int adjMat[][];
    private int nVerts;
    private int nTree;// 记录放入树中的节点个数
    private DistPar[] sPath;
    private int currentVert;
    private int startToCurrent;

    public Graph3() {
        int MAX_VERTS = 20;
        vertexList = new Vertex2[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        nTree = 0;
        for (int j = 0; j < MAX_VERTS;j++) {
            for (int k = 0; k < MAX_VERTS;k++) {
                adjMat[j][k] = INFINITY;
            }
        }
        sPath = new DistPar[MAX_VERTS];
    }

    /**
     * 添加顶点
     * @param lab
     */
    public void addVertex(char lab) {
        vertexList[nVerts++] = new Vertex2(lab);
    }

    /**
     * 添加边
     * @param start
     * @param end
     * @param weight
     */
    public void addEdge(int start, int end, int weight) {
        adjMat[start][end] = weight;
    }

    /**
     * 最短路径
     */
    public void path() {
        int startTree = 0;
        vertexList[startTree].isInTree = true;
        nTree = 1;
        for (int j = 0; j < nVerts; j++) {
            int tempDist = adjMat[startTree][j];
            sPath[j] = new DistPar(tempDist,startTree);
        }
        while (nTree < nVerts) {
            int indexMin = getMin();
            int minDist = sPath[indexMin].distance;
            if (minDist == INFINITY) {
                System.out.println(" There are unreachable vertices");
                break;
            } else {
                currentVert = indexMin;
                startToCurrent = sPath[indexMin].distance;
            }
            vertexList[currentVert].isInTree = true;
            nTree++;
            adjust_sPath();
        }
        displayPaths();

        // 清空
        nTree = 0;
        for (int j = 0; j < nVerts; j++) {
            vertexList[j].isInTree = false;
        }
    }

    private void displayPaths() {
        for (int j = 0; j < nVerts; j++) {
            System.out.print(vertexList[j].label + " = ");
            if (sPath[j].distance == INFINITY) {
                System.out.print("inf" + " ");// 无穷大
            } else {
                System.out.print(sPath[j].distance + " ");
            }
            char parent = vertexList[sPath[j].parentVert].label;
            System.out.print("(" + parent + ")");
            System.out.println();
        }
    }

    private void adjust_sPath() {
        int column = 1;
        while (column < nVerts) {
            if (vertexList[column].isInTree) {
                column++;
                continue;
            }
            //fringe 边缘
            int currentToFringe = adjMat[currentVert][column];
            int startToFringe = startToCurrent + currentToFringe;
            int sPathDist = sPath[column].distance;
            if (startToFringe < sPathDist) {
                sPath[column].parentVert = currentVert;
                sPath[column].distance = startToFringe;
            }
            column++;
        }
    }

    /**
     * 返回最小距离的索引
     * @return
     */
    private int getMin() {
        int minDist = INFINITY;// 假设最小
        int indexMin = 0;
        for (int j = 1; j < nVerts; j++) {
            // 不在树中 并 路径小于最小
            if (!vertexList[j].isInTree && sPath[j].distance < minDist) {
                // 更新最小
                minDist = sPath[j].distance;
                indexMin = j;
            }
        }
        return indexMin;
    }
}

class PathApp{
    public static void main(String[] args) {
        Graph3 g3 = new Graph3();
        g3.addVertex('a');
        g3.addVertex('b');
        g3.addVertex('c');
        g3.addVertex('d');
        g3.addVertex('e');

        g3.addEdge(0,1,50);
        g3.addEdge(0,3,80);
        g3.addEdge(1,2,60);
        g3.addEdge(1,3,90);
        g3.addEdge(2,4,40);
        g3.addEdge(2,2,20);
        g3.addEdge(3,4,70);
        g3.addEdge(4,1,50);

        System.out.println("shortest paths:");
        g3.path();
    }
}
