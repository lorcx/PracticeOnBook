package dh_design_model.decorate.fzzs;

/**
 * Created by dell on 2016/6/13.
 */
public class Person {
    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public void show(){
        System.out.println("展示：" + name);
    }
}
