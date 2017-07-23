package dh_design_model.builder;

/**
 * 建造模式的指挥者，用来控制建造过程
 * Created by lx on 2016/6/18.
 */
public class PersonDictsor {
    private PersonBuilder pb;

    public PersonDictsor(PersonBuilder pb) {
        this.pb = pb;
    }

    public void createPerson(){
        pb.buildHead();
        pb.buildBody();
        pb.buildLeftArm();
        pb.buildLeftLeg();
        pb.buildLeftRight();
        pb.buildRightArm();
    }
}
