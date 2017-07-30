package refactor_code.part10.origin;

/**
 * Created by lx on 2017/7/30.
 */
public class ParamerMethod {

    public Dollars baseCharge() {
        double result = Math.min(lastUsage(), 100) * 0.03;
        if (lastUsage() > 100) {
            result += (Math.min(lastUsage(), 200) - 100) * 0.05;
        }
        if (lastUsage() > 200) {
            result += (lastUsage() - 200) * 0.07;
        }
        return new Dollars(result);
    }

    private int lastUsage() {
        return 0;
    }
}

