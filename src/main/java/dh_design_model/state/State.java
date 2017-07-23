package dh_design_model.state;

/**
 * 抽象状态类
 * Created by lx on 2016/6/18.
 */
public abstract class State {
    abstract void handle(Context context);
}
