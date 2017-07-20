package refactor_code.part5.Remove_Assignments_to_parameters;

/**
 * Created by Administrator on 2017/7/20 0020.
 */
public class Refactor {
    // 重构 加个初始值
    int discount(int inputVal, int quantity, int yearToDate) {
        int result = inputVal;
        if (inputVal > 50) {
            result -= 2;
        }
        return result;
    }

    Object c = null;
    void foo(Object a) {
        a.toString();// 没问题
        a = c;// 修改了引用这就有问题
    }
}
