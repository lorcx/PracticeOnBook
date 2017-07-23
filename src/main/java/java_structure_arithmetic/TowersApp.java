package java_structure_arithmetic;

/**
 * Created by lx on 2016/8/27.
 */
public class TowersApp {
    static int nDisks = 3;

    public static void main(String[] args) {
        doTowers(nDisks, 'A', 'B', 'C');
    }

    /**
     * 用递归交换数据
     * @param topN
     * @param from
     * @param inter
     * @param to
     */
    private static void doTowers(int topN, char from, char inter, char to) {
        if (topN == 1) {
            System.out.println("Disk 1 from " + from + " to " + to);
        } else {
            doTowers(topN - 1, from , to ,inter);
            System.out.println("Disk " + topN + " from " + from + " to " + to);
            doTowers(topN - 1, inter, from, to);
        }
    }
}
