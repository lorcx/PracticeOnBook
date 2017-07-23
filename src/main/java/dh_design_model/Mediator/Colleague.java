package dh_design_model.Mediator;

/**
 * 抽象的同事类
 * Created by lx on 2016/6/19.
 */
public abstract class Colleague {
    protected Mediator mediator;

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }
}
