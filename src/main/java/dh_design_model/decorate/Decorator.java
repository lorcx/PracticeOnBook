package dh_design_model.decorate;

/**
 * 装饰器抽象类
 * 该类主要形成链式操作
 * Created by dell on 2016/6/13.
 */
public abstract class Decorator extends Component {
    protected Component component;//底层数据结构

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        if(component != null){
            component.operation();
        }
    }
}
