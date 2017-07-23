package dh_design_model.command;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lx on 2016/6/19.
 */
public class Waiter {
    private List<Command> orders = new ArrayList<>();
//    private Command command;

    public void setOrder(Command command) {
        if(command instanceof BakeChickenCommand){
            System.out.println("鸡翅没了，请您点写别的把");
            return;
        }
        orders.add(command);
        System.out.println("日志信息：增加1个订单 ，时间 ：" + new Date());
//        this.command = command;
    }

    /**
     * 通知执行
     */
    public void notifyCook(){
        for(Command command : orders){
            command.executeCommand();
        }
    }
}
