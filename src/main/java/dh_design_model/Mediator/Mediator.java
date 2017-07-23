package dh_design_model.Mediator;

/**
 * 抽象的中介者类
 * Created by lx on 2016/6/19.
 */
public abstract class Mediator {
    public abstract void send(String msg, Colleague colleague);
}
