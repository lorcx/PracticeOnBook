package dh_design_model.expression;

/**
 * 结束时的表达式
 * Created by dell on 2016/6/19.
 */
public class TerminalExpression extends AbstractExpression{

    @Override
    public void interpret(Context context) {
        System.out.println("终止解释器");
    }
}
