package dh_design_model.bridging;

/**
 * Created by lx on 2016/6/19.
 */
public class Abstraction {
    protected Implementor implementor;

    public void setImplementor(Implementor implementor) {
        this.implementor = implementor;
    }
    public void operation(){
        implementor.operation();
    }
}
