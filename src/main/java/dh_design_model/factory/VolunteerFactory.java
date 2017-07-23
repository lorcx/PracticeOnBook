package dh_design_model.factory;

/**
 * 社区志愿者工厂
 * Created by dell on 2016/6/14.
 */
public class VolunteerFactory implements IFactory{
    @Override
    public Leifeng createLeiFeng() {
        return new Volunteer();
    }
}
