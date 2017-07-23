package dh_design_model.flyweight;

/**
 * Created by dell on 2016/6/19.
 */
public class FlyWeightTest {
    public static void main(String[] args) {
        int extrinsicstate = 22;
        FlyWeightFactory factory = new FlyWeightFactory();
        FlyWeight fw = factory.getFlyWeight("X");
        fw.operation(--extrinsicstate);

        FlyWeight fy = factory.getFlyWeight("Y");
        fy.operation(--extrinsicstate);

        FlyWeight fz = factory.getFlyWeight("Z");
        fz.operation(--extrinsicstate);

        UnsharedConcreateFlyWeight ucf = new UnsharedConcreateFlyWeight();
        ucf.operation(--extrinsicstate);
    }
}
