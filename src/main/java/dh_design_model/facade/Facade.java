package dh_design_model.facade;

/**
 * 外观类
 * Created by dell on 2016/6/16.
 */
public class Facade {
    private SubSystemOne systemOne;
    private SubSystemThree systemThree;
    private SubSystemTwo systemTwo;

    public Facade() {
        this.systemOne = new SubSystemOne();
        this.systemThree = new SubSystemThree();
        this.systemTwo = new SubSystemTwo();
    }

    public void methodA(){
        systemOne.methodA();
        systemTwo.methodB();
    }
    public void methodB(){
        systemThree.methodC();
        systemTwo.methodB();
    }
}
