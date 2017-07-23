package think_in_java;

import java.util.Random;

/**
 * 多路分发
 * 模拟石头剪刀布游戏
 * Created by lx on 2015/12/22.
 */
@SuppressWarnings("all")
public interface Item {

    Outcome complete(Item i);

    Outcome eval(Paper p);//eval重新运算求出参数的内容

    Outcome eval(Scissors s);

    Outcome eval(Rock r);
}

/**
 * 定义石头剪刀布
 */
@SuppressWarnings("all")
enum Outcome { WIN, LOSE, DRAW }

/**
 * 布
 */
@SuppressWarnings("all")
class Paper implements Item{

    @Override
    public Outcome complete(Item i) {
        return i.eval(this);
    }

    @Override
    public Outcome eval(Paper p) {
        return Outcome.DRAW;
    }

    @Override
    public Outcome eval(Scissors s) {
        return Outcome.WIN;
    }

    @Override
    public Outcome eval(Rock r) {
        return Outcome.LOSE;
    }

    @Override
    public String toString() {
        return "布";
    }
}

/**
 * 剪刀
 */
@SuppressWarnings("all")
class Scissors implements Item{

    @Override
    public Outcome complete(Item i) {  ///第二次分发
        return i.eval(this);//多路分发
    }

    @Override
    public Outcome eval(Paper p) {
        return Outcome.LOSE;
    }

    @Override
    public Outcome eval(Scissors s) {
        return Outcome.DRAW;
    }

    @Override
    public Outcome eval(Rock r) {
        return Outcome.WIN;
    }

    @Override
    public String toString() {
        return "剪刀";
    }
}

/**
 * 石头
 */
@SuppressWarnings("all")
class Rock implements Item{

    @Override
    public Outcome complete(Item i) {
        return i.eval(this);
    }

    @Override
    public Outcome eval(Paper p) {
        return Outcome.WIN;
    }

    @Override
    public Outcome eval(Scissors s) {
        return Outcome.LOSE;
    }

    @Override
    public Outcome eval(Rock r) {
        return Outcome.DRAW;
    }

    @Override
    public String toString() {
        return "石头";
    }
}

@SuppressWarnings("all")
class RoShBom{

    private static final int SIZE = 20;
    private static Random rn = new Random(47);

    public static Item newItem(){
        switch (rn.nextInt(3)){
            default:
            case 0:
                return new Paper();
            case 1:
                return new Rock();
            case 2:
                return new Scissors();
        }
    }

    /**
     * 比赛
     */
    public static void match(Item i1,Item i2){
        System.out.println(i1 + " vs " + i2 + ":" + i1.complete(i2));//第一次分发 确定i1类型
    }

    public static void main(String[] args) {
        for (int i = 0;i < SIZE;i++){
            match(newItem(),newItem());
        }
    }
}