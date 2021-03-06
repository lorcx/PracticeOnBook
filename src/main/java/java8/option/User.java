package java8.option;

import java.util.Optional;

/**
 * @Author lx
 * @Date 2018/2/21 16:00
 */
public class User {
    private String  username;
    private String  password;
    private Integer age;
    private Address address;

    private Optional<Address> optAddress;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Optional<Address> getOptAddress() {
        return optAddress;
    }

    public void setOptAddress(Optional<Address> optAddress) {
        this.optAddress = optAddress;
    }
}
