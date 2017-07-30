package refactor_code.part9.refactor;

/**
 * 取代嵌套表达式
 * Created by lx on 2017/7/30.
 */
public class ReplaceNested {

    private boolean _isDead;
    private boolean _isSeparated;
    private boolean _isRetired;
    private double _capital;
    private double _intRate;
    private double _duration;
    private double _income;
    private double ADJ_FACTOR;

    /**
     * 重构
     * @return
     */
    double getPayAmount() {
        if (_isDead) {
            return deadAmount();
        }
        if (_isSeparated) {
            return separatedAmount();
        }
        if (_isRetired) {
            return retiredAmount();
        }
        return normalPayAmount();
    }

    /**
     * 重构：条件反转
     *
     * @return
     */
    public double getAjdustedCpital() {
        if (_capital <= 0.0 || _intRate <= 0.0 || _duration <= 0.0) {
            return 0.0;
        } else {
            return (_income / _duration) * ADJ_FACTOR;
        }
    }

    private double normalPayAmount() {
        return 0;
    }

    private double retiredAmount() {
        return 0;
    }

    private double separatedAmount() {
        return 0;
    }

    private double deadAmount() {
        return 0;
    }
}
