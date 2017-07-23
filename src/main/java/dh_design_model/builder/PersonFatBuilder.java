package dh_design_model.builder;

/**
 * 画瘦的小人
 * Created by lx on 2016/6/18.
 */
public class PersonFatBuilder extends PersonBuilder{
    public static final String personType = "胖小人的";
    public PersonFatBuilder(Hh hh) {
        super(hh);
    }

    @Override
    public void buildHead() {
        hh.draw();
        System.out.println(personType+"头");
    }

    @Override
    public void buildBody() {
        hh.draw();
        System.out.println(personType+"身体");
    }

    @Override
    public void buildLeftArm() {
        hh.draw();
        System.out.println(personType+"左胳膊");
    }

    @Override
    public void buildRightArm() {
        hh.draw();
        System.out.println(personType+"右胳膊");
    }

    @Override
    public void buildLeftLeg() {
        hh.draw();
        System.out.println(personType+"左腿");
    }

    @Override
    public void buildLeftRight() {
        hh.draw();
        System.out.println(personType+"右腿");
    }
}
