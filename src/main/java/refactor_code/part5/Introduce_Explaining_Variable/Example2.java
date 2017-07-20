package refactor_code.part5.Introduce_Explaining_Variable;

/**
 * Created by Administrator on 2017/7/20 0020.
 */
public class Example2 {
    double price() {
        double _quantity = 0;
        double _itemPrice = 0;
        return _quantity * _itemPrice -
                Math.max(0, _quantity - 500) * _itemPrice * 0.05 +
                Math.min(_quantity * _itemPrice * 0.1, 100.0);
    }
}
