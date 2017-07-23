package dh_design_model.strategy;

/**
 *
 * Created by lx on 2016/6/11.
 */
public class CashFactory {
    public static CashSuper createCashAccept(String type){
        if(type.equals("打折")){
            return new CashRebate(0.8d);
        }else if(type.equals("返利")){
            return new CashReturn(300d,100d);
        }else{
            return new CashNormal();
        }
    }
}