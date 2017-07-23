package dh_design_model.agency.meidaili;

/**
 * 追求者
 * Created by dell on 2016/6/14.
 */
public class Pursuit {
    private SchoolGril mm;

    public Pursuit(SchoolGril mm) {
        this.mm = mm;
    }

    public void giveDolls(){
        System.out.println(mm.name + "送你洋娃娃");
    }

    public void giveFlowers(){
        System.out.println(mm.name + "送你鲜花");
    }

    public void giveChocolate(){
        System.out.println(mm.name + "送你巧克力");
    }
}
