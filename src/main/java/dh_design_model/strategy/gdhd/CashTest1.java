package dh_design_model.strategy.gdhd;

/**
 * Created by lx on 2016/6/11.
 */
public class CashTest1 {
    public static void main(String[] args) {
        //策略模式相比工厂模式  只需要在客户端认识到一个类就行了
        CashContext1 context1 = new CashContext1("返利");
        System.out.println(context1.getCashResult(1000));
    }
}