package dh_design_model.builder;

/**
 * 造人抽象类 将构造与表示相分离
 * Created by lx on 2016/6/18.
 */
public abstract class PersonBuilder {
    protected Hh hh;//画图类

    public PersonBuilder(Hh hh) {
        this.hh = hh;
    }

    public abstract void buildHead();//画头
    public abstract void buildBody();//画身体
    public abstract void buildLeftArm();//画左手
    public abstract void buildRightArm();//画右手
    public abstract void buildLeftLeg();//画左腿
    public abstract void buildLeftRight();//画右腿
}
