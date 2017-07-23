package dh_design_model.decorate;

/**
 * Created by dell on 2016/6/13.
 */
public class DecorateTest {
    public static void main(String[] args) {
        ConcreteComponent cc = new ConcreteComponent();
        ConcreteDecoratorA ca = new ConcreteDecoratorA(cc);
        ConcreteDecoratorB cb = new ConcreteDecoratorB(ca);
        cb.operation();
    }
}
