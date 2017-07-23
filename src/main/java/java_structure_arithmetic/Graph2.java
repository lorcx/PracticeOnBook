package java_structure_arithmetic;

/**
 * 有向图
 * Created by lx on 2016/10/5.
 */
public class Graph2 {
    private final int INFINITY = 100000;// 无穷大
    private Vertex2[] vertexList;// 节点存储
    private int adjMat[][];// 邻边矩阵
    private int nVerts;// 当前节点的序号
    private int currentVert;// 当前结点
    private PriorityQ thePQ;
    private int nTree;

    public Graph2() {
        int MAX_VERTS = 20;
        vertexList = new Vertex2[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for (int j = 0; j < MAX_VERTS; j++) {
            for (int k = 0; k < MAX_VERTS; k++) {
                adjMat[j][k] = INFINITY;
            }
        }
        thePQ = new PriorityQ();
    }

    /**
     * 添加节点
     * @param lab
     */
    public void addVertex(char lab) {
        vertexList[nVerts++] = new Vertex2(lab);
    }

    /**
     * 添加边
     * @param:start
     * @param:end
     * @param:weight 权值
     */
    public void addEdge(int start, int end, int weight) {
        adjMat[start][end] = weight;
        adjMat[end][start] = weight;
    }

    /**
     * 显示节点
     */
    public void displayVertex(int v) {
        System.out.print(vertexList[v].label);
    }

    /**
     * 有向图最小生成树
     */
    public void mstw() {
        currentVert = 0;
        while(nTree < nVerts - 1) {
            vertexList[currentVert].isInTree = true;
            nTree++;
            for (int j = 0; j < nVerts; j++) {
                if (j == currentVert) {
                    continue;
                }
                // 在树中
                if (vertexList[j].isInTree) {
                    continue;
                }
                int distance = adjMat[currentVert][j];
                if (distance == INFINITY) {
                    continue;
                }
                putInPQ(j, distance);
            }
            if (thePQ.size() == 0) {
                System.out.println(" GRAPH NOT CONNECTED ");
                return;
            }
            Edge theEdge = thePQ.removeMin();
            int sourceVert = theEdge.srcVert;
            currentVert = theEdge.destVert;
            System.out.print(vertexList[sourceVert].label);
            System.out.print(vertexList[currentVert].label);
            System.out.print(" ");
        }

        //清空
        for (int j = 0; j < nVerts; j++) {
            vertexList[j].isInTree = false;
        }
    }

    /**
     * 放入优先级队列
     * @param:j
     * @param:distance
     */
    private void putInPQ(int newVert, int newDist) {
        int queueIndex = thePQ.find(newVert);
        //在队列中
        if (queueIndex != -1) {
            Edge tempEdge = thePQ.peekN(queueIndex);
            int oldDist = tempEdge.distance;
            // 旧的权值比新的权值大
            if (oldDist > newDist) {
                thePQ.removeN(queueIndex);
                Edge theEdge = new Edge(currentVert, newVert, newDist);
                // 插入新的边
                thePQ.insert(theEdge);
            }
        } else {
            Edge theEdge = new Edge(currentVert, newVert, newDist);
            thePQ.insert(theEdge);
        }
    }
}

class MSTWApp{
    public static void main(String[] args) {
        Graph2 g2 = new Graph2();
        g2.addVertex('a');
        g2.addVertex('b');
        g2.addVertex('c');
        g2.addVertex('d');
        g2.addVertex('e');
        g2.addVertex('f');

        g2.addEdge(0,1,6);// ab 6
        g2.addEdge(0,3,4);// ad 4
        g2.addEdge(1,2,10);// bc 10
        g2.addEdge(1,3,7);// bd 7
        g2.addEdge(1,4,7);// be 7
        g2.addEdge(2,3,8);// cd 8
        g2.addEdge(2,4,5);// ce 5
        g2.addEdge(2,5,6);// cf 6
        g2.addEdge(3,4,12);// de 12
        g2.addEdge(4,5,7);// ef 7

        System.out.println("Minimum spanning tree:");
        g2.mstw();
    }
}