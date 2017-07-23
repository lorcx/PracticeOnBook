package dh_design_model.command;

/**
 * Created by lx on 2016/6/19.
 */
public class BakeChickenCommand extends Command {
    public BakeChickenCommand(Barbecuer barbecuer) {
        super(barbecuer);
    }

    @Override
    void executeCommand() {
        System.out.println("我是厨师：烤鸡翅啦");
    }
}
