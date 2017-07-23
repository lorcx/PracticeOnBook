package think_in_java;

/**
 * 如果将enum实例向上转型，就不能调用values方法，不过可以调用别地方法
 * Created by lx on 2015/12/20.
 */
public enum Search {
    HITHER,YON //hither这边  yon那边
}

//upcast向上的
class UpcastEnum{
    public static void main(String[] args) {
        Search[] strs = Search.values();
        Enum e = Search.HITHER;//向上转型获取不到 values
//        e.values();  error
        for (Enum ee : e.getClass().getEnumConstants()){
            System.out.println(ee);
        }
    }
}

