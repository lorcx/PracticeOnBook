package dh_design_model.flyweight;

/**
 * 不需要享元的类
 * Created by dell on 2016/6/19.
 */
public class UnsharedConcreateFlyWeight extends FlyWeight {

    @Override
    public void operation(int extrinsicstate) {
        System.out.println("不共享的flyweight:" + extrinsicstate);
    }
}
