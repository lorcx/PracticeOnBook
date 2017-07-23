package dh_design_model.decorate;

/**
 * Created by dell on 2016/6/13.
 */
public class ConcreteDecoratorA extends Decorator {
    private String addState;

    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation();
        addState = "11111111";
        System.out.println("装饰器A的操作");
    }
}
