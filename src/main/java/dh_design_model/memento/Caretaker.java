package dh_design_model.memento;

/**
 * 管理者
 * Created by lx on 2016/6/18.
 */
public class Caretaker {
    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
