package dh_design_model.strategy.gdhd;


/**
 * 打折
 * Created by lx on 2016/6/11.
 */
public class CashRebate1 extends CashSuper1 {
    private double rebate;

    public CashRebate1(double rebate) {
        this.rebate = rebate;
    }

    @Override
    public double acceptCash(double money) {
        return money * rebate;
    }
}