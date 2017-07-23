package dh_design_model.command;

/**
 * 抽象的命令类
 * Created by lx on 2016/6/19.
 */
public abstract class Command {
    protected Barbecuer barbecuer;

    public Command(Barbecuer barbecuer) {
        this.barbecuer = barbecuer;
    }

    abstract void executeCommand();
}
