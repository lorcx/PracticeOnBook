package dh_design_model.visitor;

/**
 * Created by dell on 2016/6/20.
 */
public class VisitorTest {
    public static void main(String[] args) {
        ObjectStrcture os = new ObjectStrcture();
        os.attach(new ConcreateElementA());
        os.attach(new ConcreateElementB());

        ConcreateVisitorA cva = new ConcreateVisitorA();
        ConcreateVisitorB cvb = new ConcreateVisitorB();
        os.accept(cva);
        os.accept(cvb);
    }
}
