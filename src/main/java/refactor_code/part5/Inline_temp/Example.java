package refactor_code.part5.Inline_temp;

/**
 * Created by Administrator on 2017/7/20 0020.
 */
public class Example {


    private Example anOrder = new Example();

    boolean f () {
        double basePrice = anOrder.basePrice();
        return basePrice > 1000;
    }

    double basePrice() {
        return 0;
    }
}
