package dh_design_model.state;

/**
 * Created by lx on 2016/6/18.
 */
public class StateTest {
    public static void main(String[] args) {
        Context c = new Context(new ConCreateStateA());
        c.request();
        c.request();
        c.request();
        c.request();
    }
}
