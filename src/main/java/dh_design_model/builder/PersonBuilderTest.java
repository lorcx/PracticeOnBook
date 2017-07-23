package dh_design_model.builder;

/**
 * Created by lx on 2016/6/18.
 */
public class PersonBuilderTest {
    public static void main(String[] args) {
        PersonDictsor pd = new PersonDictsor(new PersonFatBuilder(new Hh()));
        pd.createPerson();
        System.out.println("-----------------------------");
        pd = new PersonDictsor(new PersonThinBuilder(new Hh()));
        pd.createPerson();
    }
}
