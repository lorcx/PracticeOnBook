package dh_design_model.strategy.cl;

/**
 * Created by lx on 2016/6/11.
 */
public class ClStrategyTest {
    public static void main(String[] args) {
        Context context = new Context(new ConcreateStrategyA());
        context.ContextInterface();
        Context context1 = new Context(new ConcreateStrategyB());
        context1.ContextInterface();
        Context context2 = new Context(new ConcreateStrategyC());
        context2.ContextInterface();
    }
}