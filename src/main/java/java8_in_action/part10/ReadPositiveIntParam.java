package java8_in_action.part10;

import org.junit.Assert;

import java.util.Optional;
import java.util.Properties;

/**
 * @Author lx
 * @Date 2018/10/27 11:46
 */
public class ReadPositiveIntParam {
    public static void main(String[] args) {
        Properties pros = new Properties();
        pros.setProperty("a", "5");
        pros.setProperty("b", "2");
        pros.setProperty("c", "true");
        pros.setProperty("d", "-3");

        Assert.assertEquals(5, readOurationImperative(pros, "a"));
        Assert.assertEquals(2, readOurationImperative(pros, "b"));
        Assert.assertEquals(0, readOurationImperative(pros, "c"));
        Assert.assertEquals(0, readOurationImperative(pros, "d"));

        Assert.assertEquals(5, readOurationWithOptional(pros, "a"));
        Assert.assertEquals(2, readOurationWithOptional(pros, "b"));
        Assert.assertEquals(0, readOurationWithOptional(pros, "c"));
        Assert.assertEquals(0, readOurationWithOptional(pros, "d"));


    }

    public static int readOurationImperative(Properties pros, String name) {
        String value = pros.getProperty(name);
        if (value != null) {
            try {
                int i = Integer.parseInt(value);
                if (i > 0) {
                    return i;
                }
            } catch (NumberFormatException e) {

            }
        }
        return 0;
    }

    public static int readOurationWithOptional(Properties pros, String name) {
        return Optional.ofNullable(pros)
                .map(prop -> prop.get(name))
                .flatMap(value -> s2i(value.toString()))
                .filter(i -> i > 0)
                .orElse(0);
    }

    /**
     * 字符串转int
     * @param s
     * @return
     */
    public static Optional<Integer> s2i(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
