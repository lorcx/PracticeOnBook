package refactor_code.part5.Inline_method;

/**
 * Created by Administrator on 2017/7/20 0020.
 */
public class Example {
    private int mumberOfLateDeliveries;

    int getRating() {
        return (moreThanFiveLateDeliveries()) ? 2 : 1;
    }

    private boolean moreThanFiveLateDeliveries() {
        return mumberOfLateDeliveries > 5;
    }
}
