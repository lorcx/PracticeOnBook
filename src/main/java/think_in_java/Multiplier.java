package think_in_java;


/**
 * Multiplier：乘法
 * Created by lx on 2015/12/24.
 */
@SuppressWarnings("all")
@ExtractInterface("IMultipler")
public class Multiplier {

    public static void main(String[] args) {
        Multiplier m = new Multiplier();
        System.out.println(m.multiply(16, 11));
    }

    /**
     * 乘(模拟乘法)
     * @return
     */
    public int multiply(int x, int y){
        int total = 0;
        for (int i = 0; i < x;i++)
//            total = add(total,y);
//            y += y; //y = y + y; // 11 + 11 22 y 22
            total = (total + y);
        return total;
    }

//    public int add(int x,int y){
//        return x + y;
//    }
}


