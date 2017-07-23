package dh_design_model.expression;

/**
 * 普通的解释器
 * Created by dell on 2016/6/19.
 */
public class NonterminalExpression extends AbstractExpression{
    @Override
    public void interpret(Context context) {
        System.out.println("非终止解释器");
    }
}
