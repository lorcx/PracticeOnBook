package dh_design_model.Observer;

/**
 * 看股票的观察者
 * Created by lx on 2016/6/18.
 */
public class StockOberver extends Observer {
    public StockOberver(String name, Subject sub) {
        super(name, sub);
    }

    @Override
    public void update() {
        System.out.println(sub.getAction() + ":" + name + "不看股票了，干活");
    }
}
