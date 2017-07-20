package refactor_code.part5.substitute_algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/20 0020.
 */
public class Example {

    String f() {

        List people = new ArrayList<>();

        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).equals("Don")) {
                return "Don";
            }
            if (people.get(i).equals("john")) {
                return "john";
            }
            if (people.get(i).equals("Kent")) {
                return "kent";
            }
        }
        return "";
    }
}
