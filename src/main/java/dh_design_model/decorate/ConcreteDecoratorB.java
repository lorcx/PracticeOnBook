package dh_design_model.decorate;

/**
 * Created by dell on 2016/6/13.
 */
public class ConcreteDecoratorB extends Decorator {

    public ConcreteDecoratorB(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation();
        behavior();
        System.out.println("装饰器B的操作");
    }

     public void behavior(){
         System.out.println("behavior 行为");
     }
}
