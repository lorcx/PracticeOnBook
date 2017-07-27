package refactor_code.part7.move_field.refactor;

/**
 * Created by Administrator on 2017/7/27 0027.
 */
public class Account {
    private AccountType _type;
    private double _interestRate;

    double inersetForAmount_days(double amount, int days) {
        return getInterestRate() * amount * days / 365;
    }

    private double getInterestRate() {
        return _interestRate;
    }
}
