package refactor_code.part5.Split_temporary_variable;

/**
 * Created by Administrator on 2017/7/20 0020.
 */
public class Example {

    private int width = 0;
    private int height = 0;

    void f() {
        double temp = 2 * (height + width);
        System.out.println(temp);
        temp = height * width;
        System.out.println(temp);
    }
}
