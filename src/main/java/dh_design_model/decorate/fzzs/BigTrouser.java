package dh_design_model.decorate.fzzs;

/**
 * Created by dell on 2016/6/13.
 */
public class BigTrouser extends Finery {
    public BigTrouser(Person person) {
        super(person);
    }

    @Override
    public void show() {
        super.show();
        System.out.println("跨裤");
    }
}
