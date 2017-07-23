package dh_design_model.visitor;

/**
 * Created by dell on 2016/6/20.
 */
public class ConcreateVisitorA extends Visitor {
    @Override
    void visitConcreateElementA(ConcreateElementA cea) {
        System.out.println("A :" + cea.getClass().getSimpleName() + "被访问了");
    }

    @Override
    void visitConcreateElementB(ConcreateElementB ceb) {
        System.out.println("A :" + ceb.getClass().getSimpleName() + "被访问了");
    }
}
