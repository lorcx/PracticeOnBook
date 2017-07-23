package dh_design_model.builder.basic;

/**
 * 抽象建造者类
 * Created by lx on 2016/6/18.
 */
public abstract class Builder {
    public abstract void builderPartA();//建造A产品
    public abstract void builderPartB();//建造B产品
    public abstract Product getResult();//显示产品
}
