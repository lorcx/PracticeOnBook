package dh_design_model.Mediator;

/**
 * Created by lx on 2016/6/19.
 */
public class ConCreateColleague1 extends Colleague {

    public ConCreateColleague1(Mediator mediator) {
        super(mediator);
    }

    public void send(String msg){
        mediator.send(msg,this);
    }

    public void notifyUser(String msg){
        System.out.println("同事1接受到的信息" + msg);
    }

}
