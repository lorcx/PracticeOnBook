package refactor_code.part5.Inline_temp;

/**
 * Created by Administrator on 2017/7/20 0020.
 */
public class Refactor {

    private Example anOrder = new Example();

    boolean f () {
        // 重构
        return anOrder.basePrice() > 1000;
    }

    double basePrice() {
        return 0;
    }
}
