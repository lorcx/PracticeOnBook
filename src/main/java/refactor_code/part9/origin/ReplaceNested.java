package refactor_code.part9.origin;

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
    private double ADJ_FACTOR;
    private double _income;

    /**
     *  取代嵌套表达式
     * @return
     */
    double getPayAmount() {
        double result;
        if (_isDead) {
            result = deadAmount();
        } else {
            if (_isSeparated) {
                result = separatedAmount();
            } else {
                if (_isRetired) {
                    result = retiredAmount();
                } else {
                    result = normalPayAmount();
                }
            }
        }
        return result;
    }

    /**
     * 条件反转
     * 
     * @return
     */
    public double getAjdustedCpital() {
        double result = 0.0;
        if (_capital > 0.0) {
            if (_intRate > 0.0 && _duration > 0.0) {
                result = (_income / _duration) * ADJ_FACTOR;
            }
        }
        return result;
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
