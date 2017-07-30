package refactor_code.part10.refactor;

import refactor_code.part10.origin.Dollars;

/**
 * Created by lx on 2017/7/30.
 */
public class ParamerMethod {

    public Dollars baseCharge() {
        double result = usageInRange(0, 100) * 0.03;
        result += usageInRange(100, 200) * 0.05;
        result += usageInRange(200, Integer.MAX_VALUE) * 0.07;
        return new Dollars(result);
    }

    public int usageInRange(int start, int end) {
        if (lastUsage() > start) {
            return Math.min(lastUsage(), end) - start;
        }
        return 0;
    }

    private int lastUsage() {
        return 0;
    }
}

