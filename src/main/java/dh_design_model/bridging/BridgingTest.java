package dh_design_model.bridging;

/**
 * Created by lx on 2016/6/19.
 */
public class BridgingTest {
    public static void main(String[] args) {
        Abstraction ab = new RefinedAbstraction();
        ab.setImplementor(new SubImplementorA());
        ab.operation();

        ab.setImplementor(new SubImplementorB());
        ab.operation();
    }
}
