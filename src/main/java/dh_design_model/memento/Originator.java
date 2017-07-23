package dh_design_model.memento;

/**
 * 发起人类
 * Created by lx on 2016/6/18.
 */
public class Originator {
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**
     * 创建备忘录
     * @return
     */
    public Memento CreateMemento(){
        return new Memento(state);
    }

    /**
     * 设置备忘录
     */
    public void setMemento(Memento memento){
        state = memento.state;
    }

    /**
     * 显示当前状态
     */
    public void show(){
        System.out.println("state=" + state);
    }
}
