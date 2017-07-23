package think_in_java;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *  18章序列化
 *  计算机CAD辅助程序
 * Created by dell on 2015/12/17.
 */
public class StoreCADState {
    public static void main(String[] args) {
        List<Class<? extends Shapee>> list = new ArrayList<Class<? extends Shapee>>();
        list.add(Circlee.class);
        list.add(Shapee.class);
        list.add(Squares.class);
        List<Shapee> shapes = new ArrayList<Shapee>();
        for (int i = 0;i < 10 ;i++){
            shapes.add(Shapee.randomFactory());
        }

        for (int i = 0;i < 10 ;i++){
            shapes.get(i).setColor(Shapee.BLUE);
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("F:\\a.out"));
            oos.writeObject(list);
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * 类型基类  应为重名\
 * deminson :尺寸
 */
abstract class Shapee implements Serializable {
    public static final int RED = 1,BLUE = 2,GREEN = 3;
    private int xPos,yPos,deminsion;
    private static Random rn = new Random(47);
    private static int count = 0;

    public abstract void setColor(int newColor);
    public abstract int getColor();

    public Shapee(int xPos, int yPos, int deminsion) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.deminsion = deminsion;
    }

    @Override
    public String toString() {
        return getClass() + "color[" + getColor() + "] xPos[" +
                xPos + "] yPos" + yPos + "] dem[" + deminsion + "]";
    }

    public static Shapee randomFactory (){
        int xVal = rn.nextInt(100);
        int yVal = rn.nextInt(100);
        int dem = rn.nextInt(100);
        switch (count++ % 3){
            default:     //如果放在最后会缺失renturn
            case 0: return new Circlee(xVal,yVal,dem);
            case 1: return new Squares(xVal,yVal,dem);
            case 2: return new Line(xVal,yVal,dem);

        }
    }
}

class Circlee extends Shapee{
    private static int color = RED;

    public Circlee(int xPos, int yPos, int deminsion) {
        super(xPos, yPos, deminsion);
    }

    @Override
    public void setColor(int newColor) {
        color = newColor;
    }

    @Override
    public int getColor() {
        return color;
    }
}

class Line extends Shapee{
    private static int color = RED;

    public Line(int xPos, int yPos, int deminsion) {
        super(xPos, yPos, deminsion);
    }

    @Override
    public void setColor(int newColor) {
        this.color = newColor;
    }

    @Override
    public int getColor() {
        return color;
    }

    public static void serializeStaticState(ObjectOutputStream oos)throws IOException {
         oos.writeInt(color);
    }

    public static void deserializeStaticState(ObjectInputStream oos)throws IOException {
        color = oos.readInt();
    }

}

class Squares extends Shapee{
    private static int color;

    public Squares(int xPos, int yPos, int deminsion) {
        super(xPos, yPos, deminsion);
        color = RED;
    }

    @Override
    public void setColor(int newColor) {
         this.color = newColor;
    }

    @Override
    public int getColor() {
        return color;
    }
}
