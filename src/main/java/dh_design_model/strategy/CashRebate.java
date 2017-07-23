package dh_design_model.strategy;

/**
 * 打折
 * Created by lx on 2016/6/11.
 */
public class CashRebate extends CashSuper {
    private double rebate;

    public CashRebate(double rebate) {
        this.rebate = rebate;
    }

    @Override
    public double acceptCash(double money) {
        return money * rebate;
    }
}