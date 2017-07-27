package refactor_code.part7.move_method.refactor;

/**
 * Created by Administrator on 2017/7/27 0027.
 */
public class AccountType {
    public boolean isPremium() {
        return false;
    }

    /**
     * 重构
     * @return
     */
    double overDraftCharge(int daysOverdrawn) {
        if (isPremium()) {
            double result = 10;
            if (daysOverdrawn > 7) {
                result += (daysOverdrawn -7) * 0.85;
            }
            return result;
        } else {
            return daysOverdrawn * 1.75;
        }
    }

}
