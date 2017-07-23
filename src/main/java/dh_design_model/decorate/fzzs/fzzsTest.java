package dh_design_model.decorate.fzzs;

/**
 * Created by dell on 2016/6/13.
 */
public class fzzsTest {
    public static void main(String[] args) {
        Finery d = new BigTrouser(new Person("aaa"));
        Finery f = new TShirts(d);
        f.show();
    }
}
