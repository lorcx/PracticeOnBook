package dh_design_model.builder.basic;

/**
 * Created by lx on 2016/6/18.
 */
public class BuilderTest {
    public static void main(String[] args) {
        Dictor d = new Dictor();
        ConCreateBuilderA c1 = new ConCreateBuilderA();
        d.construct(c1);
        Product p = c1.getResult();
        p.show();
        System.out.println("-------------------------");
        ConCreateBuilderB c2 = new ConCreateBuilderB();
        d.construct(c2);
        p = c2.getResult();
        p.show();
    }
}
