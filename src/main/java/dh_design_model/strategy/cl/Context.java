package dh_design_model.strategy.cl;

/**
 * Created by lx on 2016/6/11.
 */
public class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    //上下文接口
    public void ContextInterface(){
        //根据具体的策略模式调用其方法
        strategy.algorithmInterface();
    }
}