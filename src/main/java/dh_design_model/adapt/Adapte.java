package dh_design_model.adapt;

/**
 * 适配器类
 * Created by lx on 2016/6/18.
 */
public class Adapte extends Target{
    private Specific1 specific = new Specific1();
    public void request() {
        specific.specificRequest();
    }
}
