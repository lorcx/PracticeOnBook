package dh_design_model.flyweight;

/**
 * Created by dell on 2016/6/19.
 */
public class ConCreateFlyWeight extends FlyWeight {
    @Override
    public void operation(int extrinsicstate) {
        System.out.println("具体的flyweight:"+extrinsicstate);
    }
}
