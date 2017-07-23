package think_in_java;

import java.util.Random;

/**
 * eunm随机生成 工具
 * Created by lx on 2015/12/20.
 */
public class Enums {
    private static Random rn = new Random(47);

    public static <T extends Enum<T>> T random(Class<T> cl){
        return random(cl.getEnumConstants());
    }

    public static <T> T random(T[] values){
        return values[rn.nextInt(values.length)];
    }


}

//Activity:活动
enum Activity {
    //     避开     跳跃    下降           就坐      说谎  长期      忙碌
    RUNING, DODGING, JUMPING, FALLING, FLYING, SITTING, LYING, STANDING, HOPPING;
}

class RandomTest{
    public static void main(String[] args) {
        for (int i = 0;i < 10;i++){
            System.out.println(Enums.random(Activity.class));
        }
    }
}