package java8.option;

import java.util.Optional;

/**
 * @Author lx
 * @Date 2018/2/21 16:00
 */
public class OptionalDemo {
    public static void main(String[] args) {
//        map();

//        flatMap();


        User user = new User();
        user.setUsername("biezhi");
        user.setPassword("123456");
        user.setOptAddress(Optional.of(new Address("达尔文路", "88号")));
        user.setAge(30);
        String street = getStreet(Optional.ofNullable(user), 40);
        System.out.println(street);
    }

    public void createOptional() {
        // 声明一个空的option
        Optional<Address> addressOptional = Optional.empty();

        // 依据一个非空值创建optional
        Optional<Address> addressOptional1 = Optional.of(new Address());

        // 可接受null的optional
        Optional<Address> addressOptional2 = Optional.ofNullable(new Address());

    }

    /**
     * 使用map从optional对象中提取和转换值
     */
    public static void map() {
        Optional<Address> addressOptional = Optional.ofNullable(new Address("dads", "asd"));
        Optional<String> street = addressOptional.map(Address::getDoor);
        System.out.println(street.get());
    }

    /**
     * 使用flatMap链接Optional对象
     */
    public static void flatMap() {
        User user = new User();
        user.setUsername("biezhi");
        user.setPassword("123456");
        user.setOptAddress(Optional.of(new Address("达尔文路", "88号")));
        user.setAge(30);
        Optional<User> userOptional = Optional.of(user);
        Optional<String> stringOptional = userOptional.flatMap(User::getOptAddress).map(Address::getStreet);
        System.out.println(stringOptional.get());
    }

    public static String getStreet(Optional<User> user, int minAge) {
        /*
         * flatmap 扁平化的map
         * map用于类型转换
         * orElse如果不匹配则执行
         */
        return user.filter(u -> u.getAge() >= minAge)
                .flatMap(User::getOptAddress)
                .map(Address::getStreet)
                .orElse("qita");
    }
}
