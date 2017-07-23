package dh_design_model.bridging;

/**
 * Created by lx on 2016/6/19.
 */
public class RefinedAbstraction extends Abstraction {
    @Override
    public void operation() {
        implementor.operation();
    }
}
