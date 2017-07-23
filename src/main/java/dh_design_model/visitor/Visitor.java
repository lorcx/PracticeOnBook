package dh_design_model.visitor;

/**
 * Created by dell on 2016/6/20.
 */
public abstract class Visitor {
    abstract void visitConcreateElementA(ConcreateElementA cea);
    abstract void visitConcreateElementB(ConcreateElementB ceb);
}
