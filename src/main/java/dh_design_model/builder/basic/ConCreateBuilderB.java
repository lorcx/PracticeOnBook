package dh_design_model.builder.basic;

/**
 * 建造A产品
 * Created by lx on 2016/6/18.
 */
public class ConCreateBuilderB extends Builder{
    private Product pro = new Product();

    @Override
    public void builderPartA() {
        pro.add("组件A");
        pro.add("组件B");
        pro.add("组件C");
    }

    @Override
    public void builderPartB() {
        pro.add("组件D");
        pro.add("组件E");
        pro.add("组件F");
        pro.add("组件G");
    }

    @Override
    public Product getResult() {
        return pro;
    }
}
