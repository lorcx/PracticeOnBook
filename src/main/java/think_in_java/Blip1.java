package think_in_java;

import java.io.*;

/**
 * blip:广电
 * 可以控制的序列化
 *
 * Created by lx on 2015/12/13.
 */
public class Blip1 implements Externalizable {
    private int n;
    private String s;

    public Blip1() {
        System.out.println("blip1 默认构造方法！！");
    }

    public Blip1(int n, String s) {
        this.n = n;
        this.s = s;
    }

    @Override
    public String toString() {
        return s + n;
    }

    /**
     * 选择性的写入序列化
     * @param out
     * @throws java.io.IOException
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(s);
        out.writeInt(n);
    }

    /**
     * 选择性的反序列化
     * @param in
     * @throws java.io.IOException
     * @throws ClassNotFoundException
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        in.readObject();
        in.readInt();
    }

    public static void main(String[] args) {
        String filePath = "F:\\4.out";
        System.out.println("序列化");
        try {
            Blip1 b1 = new Blip1(47,"abc");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
            oos.writeObject(b1);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("反序列化");
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
            Blip1 b2 = (Blip1) ois.readObject();
            System.out.println(b2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


