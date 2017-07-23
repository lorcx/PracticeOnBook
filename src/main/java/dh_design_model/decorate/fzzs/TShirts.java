package dh_design_model.decorate.fzzs;

/**
 * Created by dell on 2016/6/13.
 */
public class TShirts extends Finery {
    public TShirts(Person person) {
        super(person);
    }

    @Override
    public void show() {
        super.show();
        System.out.println("T血");
    }
}
