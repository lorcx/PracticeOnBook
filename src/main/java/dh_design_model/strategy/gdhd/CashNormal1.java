package dh_design_model.strategy.gdhd;

/**
 * 正常消费
 * Created by lx on 2016/6/11.
 */
public class CashNormal1 extends CashSuper1 {
    @Override
    public double acceptCash(double money) {
        return money;
    }
}