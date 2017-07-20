
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/7/20 0020.
 */
public class Refactor {
    String f() {

        List people = new ArrayList<>();

        List candidates = Arrays.asList(new String[]{"Don", "John", "Kent"});
        for (int i = 0; i < people.size(); i++) {
            if (candidates.contains(people.get(i))) {
                return (String) people.get(i);
            }
        }
        return "";
    }
}
