package dh_design_model.factory;

/**
 * 学雷锋工厂
 * Created by dell on 2016/6/14.
 */
public class UndergraduateFactory implements IFactory {
    @Override
    public Leifeng createLeiFeng() {
        return new Undergraduate();
    }
}
