package refactor_code.part5.Replace_temp_with_query;

/**
 * Created by Administrator on 2017/7/20 0020.
 */
public class Refactor {

    double _itemPrice = 0;
    double _quantity = 0;

    double f() {
        if (basePrice() > 1000) {
            return basePrice() * 0.95;
        } else {
            return basePrice() * 0.98;
        }
    }

    double basePrice() {
        return _quantity * _itemPrice;
    }

}
