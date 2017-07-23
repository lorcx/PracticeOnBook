package dh_design_model.Observer;


/**
 * 老板 （通知者）
 * Created by lx on 2016/6/18.
 */
public class Boss extends Subject{
    public void attach(Observer ob) {
        obs.add(ob);
    }

    public void remove(Observer ob) {
        obs.remove(ob);
    }

    public void notifyObserver() {
        for (Observer ob : obs){
            ob.update();
        }
    }
}
