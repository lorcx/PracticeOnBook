package dh_design_model.agency;

/**
 * 追求者
 * Created by dell on 2016/6/14.
 */
public class Pursuit implements IGiveGift {
    SchoolGril gril;

    public Pursuit(SchoolGril gril) {
        this.gril = gril;
    }

    @Override
    public void giveDolls() {
        System.out.println(gril.name + "送洋娃娃");
    }

    @Override
    public void giveChotolate() {
        System.out.println(gril.name + "送巧克力");
    }

    @Override
    public void giveFlowers() {
        System.out.println(gril.name + "送花");
    }
}
