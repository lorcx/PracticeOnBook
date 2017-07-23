package dh_design_model.strategy;

/**
 * 正常消费
 * Created by lx on 2016/6/11.
 */
public class CashNormal extends CashSuper {
    @Override
    public double acceptCash(double money) {
        return money;
    }
}