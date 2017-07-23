package dh_design_model.Mediator;

/**
 * Created by lx on 2016/6/19.
 */
public class MediatorTest {
    public static void main(String[] args) {
        ConCreateMediator m = new ConCreateMediator();
        ConCreateColleague1 c1 = new ConCreateColleague1(m);
        ConCreateColleague2 c2 = new ConCreateColleague2(m);

        m.setColleague1(c1);
        m.setColleague2(c2);
        c1.send("吃了吗");
        c2.send("吃完了");
    }
}
