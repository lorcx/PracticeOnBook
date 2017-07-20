package refactor_code.part5.Introduce_Explaining_Variable;

/**
 * Created by Administrator on 2017/7/20 0020.
 */
public class Refactor3 {
    double _quantity = 0;
    double _itemPrice = 0;

    // 提炼临时变量重构
    double price() {
        return basePrice() - quantityDiscount() + shipping();
    }

    double basePrice() {
        return _quantity * _itemPrice;
    }

    double quantityDiscount() {
        return Math.max(0, _quantity - 500) * _itemPrice * 0.05;
    }

    double shipping() {
        return Math.min(basePrice() * 0.1, 100.0);
    }

}
