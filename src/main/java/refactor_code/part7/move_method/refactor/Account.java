package refactor_code.part7.move_method.refactor;

/**
 * Created by Administrator on 2017/7/27 0027.
 */
public class Account {
    private AccountType _type;
    private int _daysOverDrawn;

    /**
     * 重构 移动方法变成委托函数
     * @return
     */
    double overDraftCharge() {
        return _type.overDraftCharge(_daysOverDrawn);
    }

    double bankCharge() {
        double result = 4.5;
        if (_daysOverDrawn > 0) {
            result += overDraftCharge();
        }
        return result;
    }

    /**
     * 重构去掉委托函数
     * @return
     */
    double bankCharge1() {
        double result = 4.5;
        if (_daysOverDrawn > 0) {
            result += _type.overDraftCharge(_daysOverDrawn);
        }
        return result;
    }
}
