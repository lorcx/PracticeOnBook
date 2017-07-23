package dh_design_model.Mediator;

/**
 * Created by lx on 2016/6/19.
 */
public class ConCreateMediator extends Mediator {
    private ConCreateColleague1 colleague1;
    private ConCreateColleague2 colleague2;

    public void setColleague1(ConCreateColleague1 colleague1) {
        this.colleague1 = colleague1;
    }

    public void setColleague2(ConCreateColleague2 colleague2) {
        this.colleague2 = colleague2;
    }

    /**
     * 中介给每位同时发信息
     * @param msg
     * @param colleague
     */
    @Override
    public void send(String msg, Colleague colleague) {
        if(colleague1 == colleague){
            colleague1.notifyUser(msg);
        }else{
            colleague2.notifyUser(msg);
        }
    }
}
