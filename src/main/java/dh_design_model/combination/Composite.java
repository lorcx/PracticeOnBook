package dh_design_model.combination;

import java.util.ArrayList;
import java.util.List;

/**
 * 枝节点行为
 * Created by lx on 2016/6/19.
 */
public class Composite extends Component {
    List<Component> list = new ArrayList<Component>();

    public Composite(String name) {
        super(name);
    }

    @Override
    public void add(Component component) {
        list.add(component);
    }

    @Override
    public void remove(Component component) {
        list.remove(component);
    }

    @Override
    public void display(int depth) {
        StringBuilder path = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            path.append("-");
        }
        path.append(name);
        System.out.println(path);
        for (Component cc : list) {
            cc.display(depth + 2); //+2为了显示更明显
        }
    }
}
