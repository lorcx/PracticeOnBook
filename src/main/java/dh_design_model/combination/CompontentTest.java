package dh_design_model.combination;

/**
 * Created by lx on 2016/6/19.
 */
public class CompontentTest {
    public static void main(String[] args) {
        Component root = new Composite("root");
        root.add(new Leaf("子A"));
        root.add(new Leaf("子B"));

        Component comp = new Composite("二级 X");
        comp.add(new Leaf("子C"));
        comp.add(new Leaf("子D"));
        comp.add(new Leaf("子E"));
        root.add(comp);

        Component comp2 = new Composite("二级 Y");
        comp2.add(new Leaf("子Q"));
        comp2.add(new Leaf("子Y"));
        comp2.add(new Leaf("子U"));
        root.add(comp2);

        Component comp3 = new Composite("三级 X");
        comp3.add(new Leaf("子p"));
        comp3.add(comp3);

        root.display(1);
    }
}
