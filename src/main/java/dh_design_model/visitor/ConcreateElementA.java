package dh_design_model.visitor;

/**
 * Created by dell on 2016/6/20.
 */
public class ConcreateElementA extends Element {

    @Override
    void accept(Visitor visitor) {
        visitor.visitConcreateElementA(this);
    }
    public void operationB(){
        System.out.println("其他相关方法");
    }

}
