package refactor_code.part5.Split_temporary_variable;

/**
 * Created by Administrator on 2017/7/20 0020.
 */
public class Refactor {

    private int width = 0;
    private int height = 0;

    void f() {
        // 同一个临时变量承担了2个不同的事情，会令代码阅读者糊涂
        final double temp = 2 * (height + width);
        System.out.println(temp);
        final double area = height * width;
        System.out.println(area);
    }

}
