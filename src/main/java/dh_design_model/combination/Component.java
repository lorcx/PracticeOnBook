package dh_design_model.combination;

/**
 * 公共的抽象类，用于访问和管理子部件
 * Created by lx on 2016/6/19.
 */
public abstract class Component {
    protected String name;

    public Component(String name) {
        this.name = name;
    }

    public abstract void add(Component component);
    public abstract void remove(Component component);
    public abstract void display(int depth);
}
