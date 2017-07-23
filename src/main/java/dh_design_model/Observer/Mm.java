package dh_design_model.Observer;

/**
 * 前台美眉
 * Created by lx on 2016/6/18.
 */
public class Mm extends Subject{
    @Override
    public void attach(Observer ob) {
        obs.add(ob);
    }

    @Override
    public void remove(Observer ob) {
        obs.remove(ob);
    }

    @Override
    public void notifyObserver() {
        for (Observer ob : obs){
            ob.update();
        }
    }
}
