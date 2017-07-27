package refactor_code.part7.move_method.origin;

/**
 * Created by Administrator on 2017/7/27 0027.
 */
public class Account {
    private AccountType _type;
    private int _daysOverDrawn;

    double overDraftCharge() {
        if (_type.isPremium()) {
            double result = 10;
            if (_daysOverDrawn > 7) {
                result += (_daysOverDrawn -7) * 0.85;
            }
            return result;
        } else {
            return _daysOverDrawn * 1.75;
        }
    }

    double bankCharge() {
        double result = 4.5;
        if (_daysOverDrawn > 0) {
            result += overDraftCharge();
        }
        return result;
    }
}
