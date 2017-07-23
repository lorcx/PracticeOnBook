package dh_design_model.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 通知者
 * Created by lx on 2016/6/18.
 */
public abstract class Subject {
    protected List<Observer> obs = new ArrayList<Observer>();
    private String action;
    public abstract void attach(Observer ob);//添加观察者
    public abstract void remove(Observer ob);//移除观察者
    public abstract void notifyObserver();//通知所有观察者

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
