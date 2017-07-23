package dh_design_model.template;

/**
 * 模板类
 * Created by dell on 2016/6/15.
 */
public abstract  class AbstractClass {
    protected abstract void primitiveOperation1();
    abstract void primitiveOperation2();

    /**
     * 模板方法，给出了逻辑骨架
     */
    public void template(){
        primitiveOperation1();
        primitiveOperation2();
    }
}
