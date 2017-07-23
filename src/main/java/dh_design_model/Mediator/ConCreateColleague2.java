package dh_design_model.Mediator;

/**
 * Created by lx on 2016/6/19.
 */
public class ConCreateColleague2 extends Colleague {

    public ConCreateColleague2(Mediator mediator) {
        super(mediator);
    }

    public void send(String msg){
        mediator.send(msg,this);
    }

    public void notifyUser(String msg){
        System.out.println("同事2接受到的信息" + msg);
    }

}
