package dh_design_model.state;

/**
 * Created by lx on 2016/6/18.
 */
public class ConCreateStateB extends State {
    @Override
    void handle(Context context) {
        context.setState(new ConCreateStateA());
    }
}
