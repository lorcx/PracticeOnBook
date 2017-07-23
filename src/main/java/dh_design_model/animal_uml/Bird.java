package dh_design_model.animal_uml;

/**
 * 鸟
 * Created by lx on 2016/6/11.
 */
public abstract class Bird extends Animal {
    private Wing wing;//翅膀
    public Bird(){
        wing = new Wing();//组合关系
    }
}