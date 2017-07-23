package dh_design_model.Observer;

/**
 * 看nba的观察者
 * Created by lx on 2016/6/18.
 */
public class NbaOberver extends Observer {

    public NbaOberver(String name, Subject sub) {
        super(name, sub);
    }

    @Override
    public void update() {
        System.out.println(sub.getAction() + ":" + name + "nba不看了");
    }
}
