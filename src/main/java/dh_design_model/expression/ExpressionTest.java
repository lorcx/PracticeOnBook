package dh_design_model.expression;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2016/6/19.
 */
public class ExpressionTest {
    public static void main(String[] args) {
        Context context = new Context();
        List<AbstractExpression> list = new ArrayList<AbstractExpression>();
        list.add(new TerminalExpression());
        list.add(new NonterminalExpression());
        list.add(new TerminalExpression());
        list.add(new TerminalExpression());

        for(AbstractExpression ae : list){
            ae.interpret(context);
        }
    }
}
