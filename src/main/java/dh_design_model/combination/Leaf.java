package dh_design_model.combination;

/**
 * 叶子节点
 * Created by lx on 2016/6/19.
 */
public class Leaf extends Component{

    public Leaf(String name) {
        super(name);
    }

    @Override
    public void add(Component component) {
        System.out.println("不能添加");
    }

    @Override
    public void remove(Component component) {
        System.out.println("该节点下没有可以删除的子节点");
    }

    @Override
    public void display(int depth) {
        StringBuilder path = new StringBuilder();
        for(int i = 0;i < depth;i++){
            path.append("-");
        }
        path.append(name);
        System.out.println(path);
    }
}
