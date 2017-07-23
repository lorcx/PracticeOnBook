package dh_design_model.strategy.gdhd;

/**
 * 返利
 * Created by lx on 2016/6/11.
 */
public class CashReturn1 extends CashSuper1 {
    private double cashReturn;//返多少
    private double cashCondition;//满多少

    public CashReturn1(double cashCondition, double cashReturn) {
        this.cashReturn = cashReturn;
        this.cashCondition = cashCondition;
    }

    @Override
    public double acceptCash(double money) {
        double result = money;
        //当前现金 - 返利的钱               算出能够返利几次
        if(money >= cashCondition){
            result = money - (Math.floor(money / cashCondition) * cashReturn);
        }
        return result;
    }
}
