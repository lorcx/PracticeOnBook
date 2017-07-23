package dh_design_model.decorate.fzzs;

/**
 * 服装类
 * Created by dell on 2016/6/13.
 */
public class Finery extends Person {
    private Person person;

    public Finery(Person person) {
        super();
        this.person = person;
    }

    @Override
    public void show() {
        if(person != null){
            person.show();
        }
    }
}
