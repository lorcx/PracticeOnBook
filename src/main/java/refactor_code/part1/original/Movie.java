package refactor_code.part1.original;

/**
 * Created by Administrator on 2017/7/13 0013.
 */
public class Movie {
    public static final int CHILDRENS = 2;// 孩子
    public static final int REGULAR = 0;// 正规的
    public static final int NEW_RELEASE = 1;// 新发行的

    private String _title;
    private int _priceCode;

    public Movie(String _title, int _priceCode) {
        this._title = _title;
        this._priceCode = _priceCode;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public int get_priceCode() {
        return _priceCode;
    }

    public void set_priceCode(int _priceCode) {
        this._priceCode = _priceCode;
    }
}
