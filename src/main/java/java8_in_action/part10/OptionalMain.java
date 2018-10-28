package java8_in_action.part10;

import java.util.Optional;

/**
 * @Author lx
 * @Date 2018/10/27 11:08
 */
public class OptionalMain {
    public static void main(String[] args) {
        Person person = new Person();
        Car car = new Car();
        Insurance insurance = new Insurance();

        insurance.setName("abcd");
        car.setInsurance(Optional.of(insurance));
        person.setCar(Optional.of(car));

        OptionalMain om = new OptionalMain();
        String carInsuranceName = om.getCarInsuranceName(Optional.of(person));
        System.out.println(carInsuranceName);
    }

    /**
     * 获取汽车保险公司名称
     * @param person
     * @return
     */
    public String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("unknown");// 不存在则返回unknown
    }

    /*public Set<String> getCarInsuranceNames(List<Person> persons) {
        return persons.stream()
                .map(Person::getCar)
                .map(optCar -> optCar.flatMap(Car::getInsurance))
                .map(optInsurance -> optInsurance.map(Insurance::getName))
                .flatMap(Optional::stream)
                .collect(toSet());
    }*/
}
