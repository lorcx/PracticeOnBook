package dh_design_model.command;

/**
 * Created by lx on 2016/6/19.
 */
public class CommandTest {
    public static void main(String[] args) {
        Barbecuer barbecuer = new Barbecuer();//烤肉者
        Command bakeMutton1 = new BakeMuttonCommand(barbecuer);
        Command bakeMutton2 = new BakeMuttonCommand(barbecuer);
        Command bakeChicken1 = new BakeChickenCommand(barbecuer);
        Waiter waiter = new Waiter();

        waiter.setOrder(bakeChicken1);
        waiter.setOrder(bakeMutton2);
        waiter.setOrder(bakeMutton1);
        waiter.notifyCook();//通知厨师
    }
}
