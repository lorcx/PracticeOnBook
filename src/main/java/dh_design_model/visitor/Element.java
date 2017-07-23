package dh_design_model.visitor;

/**
 * Created by dell on 2016/6/20.
 */
public abstract class Element {
    abstract void accept(Visitor visitor);
}
