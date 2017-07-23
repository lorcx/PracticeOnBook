package dh_design_model.agency;


/**
 * 代理模式测试
 * Created by dell on 2016/6/14.
 */
public class ProxyTest {
    public static void main(String[] args) {
        SchoolGril gril = new SchoolGril("李娇娇");
        Proxy p = new Proxy(gril);
        p.giveDolls();
        p.giveFlowers();
        p.giveChotolate();
    }
}
