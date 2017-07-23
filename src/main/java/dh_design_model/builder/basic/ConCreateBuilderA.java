package dh_design_model.builder.basic;

/**
 * 建造A产品
 * Created by lx on 2016/6/18.
 */
public class ConCreateBuilderA extends Builder{
    private Product pro = new Product();

    @Override
    public void builderPartA() {
        pro.add("组件1");
        pro.add("组件2");
        pro.add("组件3");
    }

    @Override
    public void builderPartB() {
        pro.add("组件4");
        pro.add("组件5");
        pro.add("组件6");
        pro.add("组件7");
    }

    @Override
    public Product getResult() {
        return pro;
    }
}
