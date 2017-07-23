package dh_design_model.command;


/**
 * 烤羊肉
 * Created by lx on 2016/6/19.
 */
public class BakeMuttonCommand extends Command {
    public BakeMuttonCommand(Barbecuer barbecuer) {
        super(barbecuer);
    }

    @Override
    void executeCommand() {
        System.out.println("我是厨师：烤羊肉啦");
    }
}
