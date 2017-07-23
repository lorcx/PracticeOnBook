package dh_design_model.expression.play;

/**
 * Created by dell on 2016/6/19.
 */
public class PlayTest {
    public static void main(String[] args) {
        PlayContext pc = new PlayContext();
        pc.setText(" o 2 e 0.5 ");
        Expression expression = null;
        while (pc.getText().length() > 0){
            String str = pc.getText().substring(0,1);
            if(str.equals("o")){
                expression = new Scale();
            }else{
                expression = new Note();
            }
        }
    }
}
