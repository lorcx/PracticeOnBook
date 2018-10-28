package java8_in_action.part10;

import java.util.Optional;

/**
 * @Author lx
 * @Date 2018/10/27 10:59
 */
public class Person {
    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }

    public void setCar(Optional<Car> car) {
        this.car = car;
    }
}
