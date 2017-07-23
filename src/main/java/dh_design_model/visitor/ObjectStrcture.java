package dh_design_model.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 枚举它的元素
 * Created by dell on 2016/6/20.
 */
public class ObjectStrcture {
    private List<Element> list = new ArrayList<Element>();

    /**
     * 添加
     * @param element
     */
    public void attach(Element element){
        list.add(element);
    }

    /**
     * 删除
     * @param element
     */
    public void remove(Element element){
        list.remove(element);
    }

    /**
     * 删除
     * @param element
     */
    public void accept(Visitor visitor){
        for(Element e : list){
            e.accept(visitor);
        }
    }

}
