package dh_design_model.expression.play;

/**
 * 音符类
 * Created by dell on 2016/6/19.
 */
public class Note extends Expression{
    @Override
    public void excute(String key, double value) {
        String note = "";
        if(key.equals("c")){
            note = "1";
        }else if(key.equals("d")){
            note = "2";
        }else if(key.equals("e")){
            note = "3";
        }else if(key.equals("f")){
            note = "4";
        }else if(key.equals("g")){
            note = "5";
        }else if(key.equals("a")){
            note = "6";
        }else{
            note = "7";
        }
        System.out.println(note);
    }
}
