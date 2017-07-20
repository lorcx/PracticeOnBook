package refactor_code.part5.Remove_Assignments_to_parameters;

/**
 * Created by Administrator on 2017/7/20 0020.
 */
public class Example {
    int discount(int inputVal, int quantity, int yearToDate) {
        if (inputVal > 50) {
            inputVal -= 2;
        }
        return 0;
    }
}
