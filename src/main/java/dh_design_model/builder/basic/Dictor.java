package dh_design_model.builder.basic;

/**
 * 指挥者类
 * Created by lx on 2016/6/18.
 */
public class Dictor {
    public void construct(Builder builder){
        builder.builderPartA();
        builder.builderPartB();
    }
}
