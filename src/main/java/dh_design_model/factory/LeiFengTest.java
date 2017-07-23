package dh_design_model.factory;

/**
 * Created by dell on 2016/6/14.
 */
public class LeiFengTest {
    public static void main(String[] args) {
        IFactory factory = new UndergraduateFactory();
        Leifeng l = factory.createLeiFeng();
        l.buyRice();
        l.sweep();
        l.wash();
    }
}
