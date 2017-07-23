package think_in_java;

import java.io.*;
import java.util.Random;

/**
 * 对象序列化
 *
 * 2015.12.13此时的理解：
 *      递归调用就像[栈]一样
 *      执行递归结束后从最后一个向前一直输出， (1)xn, (2)xn-1 xn,(3) xn-2 xn-1 xn,.......
 *      输出递归时从第一个-到最后正着输出
 *      random 总之：相同的种子，相同的范围，会得到相同顺序的随机数据。
 *
 * Created by lx on 2015/12/13.
 */
public class Worm implements Serializable {
    private static Random rn = new Random(47);
    private Data[] d = {
            new Data(rn.nextInt(10)),
            new Data(rn.nextInt(10)),
            new Data(rn.nextInt(10))
    };

    private Worm next;
    private char c;

    public Worm() {
        System.out.println("默认");
    }

    public Worm(int i, char x) {
        System.out.println("worm 构造方法" + i);
        c = x;
        if(--i > 0){
            next = new Worm(i,(char)(x + 1));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(":");
        sb.append(c);
        sb.append("(");
        for (Data da :d){
            sb.append(da);
        }
        sb.append(")");
        if(next != null){
            sb.append(next);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String filePath = "F:\\5.out";
        Worm w = new Worm(6,'a');
        System.out.println("worm" + w);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
            oos.writeObject("write object\n");
            oos.writeObject(w);
            oos.close();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
            String msg = (String) ois.readObject();
            Worm wo = (Worm) ois.readObject();
            System.out.println(msg + ": worm :+" + wo );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class Data implements Serializable {
    private int n;

    public Data(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return String.valueOf(n);
    }
}