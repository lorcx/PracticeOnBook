package dh_design_model.state;

/**
 * Created by lx on 2016/6/18.
 */
public class Context {
    private State state;

    public Context(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        System.out.println("当前状态="+state.getClass().getSimpleName());
        this.state = state;
    }

    public void request(){
        state.handle(this);
    }
}
