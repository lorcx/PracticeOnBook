package refactor_code.part5.Replace_temp_with_query;

/**
 * Created by Administrator on 2017/7/20 0020.
 */
public class Refactor1 {

    double _itemPrice = 0;
    double _quantity = 0;

    double f() {
        // 将赋值一次变量设置为final 如果有问题编译器就会警告我
        return basePrice() * discountFactor();
    }

    double basePrice() {
        return _quantity * _itemPrice;
    }

    double discountFactor() {
        final double discountFactor;
        if (basePrice() > 1000) {
            discountFactor = 0.95;
        } else {
            discountFactor = 0.98;
        }
        return discountFactor;
    }
}
