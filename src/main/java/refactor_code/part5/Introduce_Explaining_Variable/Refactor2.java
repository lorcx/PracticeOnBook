package refactor_code.part5.Introduce_Explaining_Variable;

/**
 * Created by Administrator on 2017/7/20 0020.
 */
public class Refactor2 {

    double _quantity = 0;
    double _itemPrice = 0;

    // 提炼临时变量重构
    double price() {
        // 基本价格
        final double basePrice = _quantity * _itemPrice;
        // 批发折扣
        final double quantityDiscount = Math.max(0, _quantity - 500) * _itemPrice * 0.05;
        // 运费
        final double shipping = Math.min(basePrice * 0.1, 100.0);
        return basePrice - quantityDiscount + shipping;
    }
}
