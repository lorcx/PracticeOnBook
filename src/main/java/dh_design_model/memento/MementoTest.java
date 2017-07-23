package dh_design_model.memento;

/**
 * Created by lx on 2016/6/18.
 */
public class MementoTest {
    public static void main(String[] args) {
        Originator o = new Originator();
        o.setState("aaaa");
        o.show();

        Caretaker c = new Caretaker();
        c.setMemento(o.CreateMemento());

        o.setState("bbbbbb");
        o.show();

        o.setMemento(c.getMemento());
        o.show();
    }
}
