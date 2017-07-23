package dh_design_model.strategy.gdhd;

/**
 * Created by lx on 2016/6/11.
 */
public class CashContext1 {
    private CashSuper1 cashSuper;

    public CashContext1(String type) {
        if(type.equals("打折")){
            cashSuper = new CashRebate1(0.8d);
        }else if(type.equals("返利")){
            cashSuper = new CashReturn1(300d,100d);
        }else{
            cashSuper = new CashNormal1();
        }
    }

    public double getCashResult(double money) {
        return cashSuper.acceptCash(money);
    }
}
