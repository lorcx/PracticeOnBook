package programmer_math;

/**
 * 汉诺塔游戏
 * Created by lx on 2016/11/12.
 */
public class Hnt {
    public static void main(String[] args) {
        hani(6, 'x', 'y', 'z');
    }

    /**
     * 汉诺塔
     *
     * @param n:第几个
     * @param x：起始
     * @param y：目标
     * @param z：中转
     */
    public static void hani(int n, char x, char y, char z) {
        if (n != 0) {
            hani(n - 1, x, z, y);
            System.out.println(x + "=>" + y);
            hani(n - 1, z, y, x);
        }
    }
}
