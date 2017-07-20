package refactor_code.part5.Inline_method;

/**
 * Created by Administrator on 2017/7/20 0020.
 */
public class Refactor {
    private int mumberOfLateDeliveries;

    int getRating() {
        // 重构
        return (mumberOfLateDeliveries > 5) ? 2 : 1;
    }
}
