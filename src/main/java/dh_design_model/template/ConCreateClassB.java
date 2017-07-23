package dh_design_model.template;

/**
 * Created by dell on 2016/6/15.
 */
public class ConCreateClassB extends AbstractClass {
    @Override
    protected void primitiveOperation1() {
        System.out.println("具体B实现");
    }

    @Override
    void primitiveOperation2() {
        System.out.println("具体C实现");
    }
}
