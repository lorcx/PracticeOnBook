package dh_design_model.builder.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * 产品类
 * Created by lx on 2016/6/18.
 */
public class Product {
    private List<String> parts = new ArrayList<>();//存放组件

    /**
     * 添加组件
     * @param part
     */
    public void add(String part){
        parts.add(part);
    }

    /**
     * 展示组件
     */
    public void show(){
        for(String part : parts){
            System.out.println(part);
        }
    }
}
