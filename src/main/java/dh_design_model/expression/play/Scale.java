package dh_design_model.expression.play;

/**
 * 音符类
 * Created by dell on 2016/6/19.
 */
public class Scale extends Expression {
    @Override
    public void excute(String key, double value) {
        String scale = "";
        switch ((int) value){
            case 1:
                scale = "低音";
                break;
            case 2:
                scale = "中音";
                break;
            case 3:
                scale = "高音";
                break;
        }
        System.out.println(scale);
    }
}
