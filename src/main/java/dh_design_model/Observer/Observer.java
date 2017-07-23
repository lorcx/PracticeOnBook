package dh_design_model.Observer;

/**
 * 抽象的观察者
 * Created by lx on 2016/6/18.
 */
public abstract class Observer {
    public Subject sub;
    public String name;
    public Observer(String name,Subject sub) {
        this.sub = sub;
        this.name = name;
    }

    public abstract void update();
}
