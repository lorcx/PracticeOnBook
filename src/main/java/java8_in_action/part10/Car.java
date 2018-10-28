package java8_in_action.part10;

import java.util.Optional;

/**
 * @Author lx
 * @Date 2018/10/27 10:59
 */
public class Car {

    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }

    public void setInsurance(Optional<Insurance> insurance) {
        this.insurance = insurance;
    }
}
