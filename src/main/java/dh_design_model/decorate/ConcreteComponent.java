package dh_design_model.decorate;

/**
 * 抽象的实际对象
 * Created by dell on 2016/6/13.
 */
public class ConcreteComponent extends Component{
    @Override
    public void operation() {
        //默认的操作
        System.out.println("具体的对象操作");
    }
}
