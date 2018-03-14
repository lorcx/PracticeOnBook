package java8_in_action.part2;

/**
 * @Author: lx
 * @Date: Created in 2018/3/14 0014
 */
public class MeaningOfThis {
    public final int value = 4;

    public void doIt() {
        int value = 6;
        Runnable r  = new Runnable() {
            public final int value = 5;
            @Override
            public void run() {
                int value = 10;
                System.out.println(this.value);
            }
        };
        r.run();
    }

    public static void main(String[] args) {
        MeaningOfThis mot = new MeaningOfThis();
        mot.doIt();
    }
}
