package dh_design_model.strategy;

/**
 * Created by lx on 2016/6/11.
 */
public class CashTest {
    public static void main(String[] args) {
        CashSuper cash = CashFactory.createCashAccept("返利");
        System.out.println(cash.acceptCash(1000));
    }
}