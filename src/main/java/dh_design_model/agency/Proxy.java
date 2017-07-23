package dh_design_model.agency;


/**
 * 代理者
 * Created by dell on 2016/6/14.
 */
public class Proxy implements IGiveGift{
    Pursuit pursuit;

    public Proxy(SchoolGril gril) {
        this.pursuit = new Pursuit(gril);
    }

    @Override
    public void giveDolls() {
        System.out.println("hello");
        pursuit.giveDolls();
        System.out.println("怎么样");
    }

    @Override
    public void giveChotolate() {
        System.out.println("想不想来点巧克力。");
        pursuit.giveChotolate();
        System.out.println("快尝尝");
    }

    @Override
    public void giveFlowers() {
        System.out.println("知道你喜欢洋娃娃");
        pursuit.giveFlowers();
        System.out.println("喜欢吗");
    }
}
