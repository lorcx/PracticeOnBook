package java_structure_arithmetic;

/**
 * Graph 图
 * Created by lx on 2016/10/4.
 */
public class Graph {
    private final int MAX_VERTS = 20;// 最大的顶点个数
    private Vertex[] vertexList;// 存储结点
    private int[][] adjMat;// 邻接矩阵
    private int nVertex;// 当前结点个数
    private StackX theStack;// 用于深度优先搜索
    private QueueBfs theQueue;// 用于广度优先搜索

    public Graph() {
        this.vertexList = new Vertex[MAX_VERTS];
        this.adjMat = new int[MAX_VERTS][MAX_VERTS];
        this.nVertex = 0;
        for (int i = 0; i < adjMat.length; i++) {
            for (int j = 0; j < adjMat[i].length; j++) {
                adjMat[i][j] = 0;
            }
        }
        theStack = new StackX(MAX_VERTS);
        theQueue = new QueueBfs(MAX_VERTS);
    }

    /**
     * 添加顶点
     */
    public void addVertex(char label) {
        vertexList[nVertex++] = new Vertex(label);
    }

    /**
     * 添加边（邻接矩阵）
     */
    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
        adjMat[end][start] = 1;// 图是无向无权的 ，所以要双向加
    }

    /**
     * 显示节点信息
     */
    public void displayVertex(int n){
        System.out.print(vertexList[n].getLabel() + " ");
    }

    /**
     * 深度优先搜索
     * 规则1：如果可能，访问一个邻接的未访问的节点，标记它，把他放入栈中
     * 规则2：当不能执行规则1时，如果栈不为空，则弹出栈顶
     * 规则3：如果不能执行规则1规则2，那么整个搜索结束
     *
     * 首先peek检查栈顶
     * 找到这个顶点还未访问的邻接点
     * 没找到出栈，找到入栈
     */
    public void dfs() {
        vertexList[0].setWasVisit(true);// 标记访问
        theStack.push(0);
        displayVertex(0);
        while (!theStack.isEmpty()) {
            int v = getAdjUnvisitedVertex((int) theStack.peek());
            if (v == -1) {// 没有未被访问的邻接点
                theStack.pop();
            } else {
                vertexList[v].setWasVisit(true);
                theStack.push(v);
                displayVertex(v);
            }
        }
        // 清空
        for (int i =0; i < nVertex; i++) {
             vertexList[i].setWasVisit(false);
        }
    }

    /**
     * 指定行为访问的节点
     * @return
     */
    private int getAdjUnvisitedVertex(int v) {
        for (int j = 0; j < nVertex; j++) {
            // 两个节点是连接的并且没有被访问
            if (adjMat[v][j] == 1 && !vertexList[j].isWasVisit()) {
                return j;
            }
        }
        return -1;
    }


    /**
     * 广度优先搜索
     * 规则1：访问下一个未来访问的邻接点（如果存在），这个节点必须是当前顶点的邻接点，标记它，并
     *       把它插入到队列中
     * 规则2：如果因为已经没有未访问顶点而不能执行规则1，那么从队列头取一个顶点（如果存在），并使其
     *        作为顶点
     * 规则3：如果因为队列为空而不能执行规则2，则搜索结束
     *
     */
    public void bfs() throws Exception {
        vertexList[0].setWasVisit(true);// 标记访问
        displayVertex(0);
        theQueue.insert(0);
        int v2;
        while (!theQueue.isEmpty()) {
            int v1 = (int) theQueue.remove();
            while ((v2 = getAdjUnvisitedVertex(v1)) != -1){
                vertexList[v2].setWasVisit(true);
                displayVertex(v2);
                theQueue.insert(v2);
            }
        }
        // 清空
        for (int i =0; i < nVertex; i++) {
            vertexList[i].setWasVisit(false);
        }
    }

    /**
     * 最小生成树
     */
    public void mnt() {
        vertexList[0].setWasVisit(true);
        theStack.push(0);
        while(!theStack.isEmpty()) {
            int currentVertex = (int) theStack.peek();// 当前节点
            int v = getAdjUnvisitedVertex(currentVertex);
            if (v == -1) {
                theStack.pop();
            } else {
                vertexList[v].setWasVisit(true);
                theStack.push(v);
                displayVertex(currentVertex);
                displayVertex(v);
                System.out.print(" ");
            }
        }
        // 清空
        for (int i =0; i < nVertex; i++) {
            vertexList[i].setWasVisit(false);
        }
    }
}

class GraphTest {

    public static void main(String[] args) throws Exception {
        Graph g = new Graph();
        g.addVertex('a');
        g.addVertex('b');
        g.addVertex('c');
        g.addVertex('d');
        g.addVertex('e');
//        g.addEdge(0,1);// ab
//        g.addEdge(1,2);// bc
//        g.addEdge(0,3);// ad
//        g.addEdge(3,4);// de

//        g.dfs();
//        g.bfs();

        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(0,3);
        g.addEdge(0,4);
        g.addEdge(1,2);
        g.addEdge(1,3);
        g.addEdge(1,4);
        g.addEdge(2,3);
        g.addEdge(2,4);
        g.addEdge(3,4);
        g.mnt();

    }
}
