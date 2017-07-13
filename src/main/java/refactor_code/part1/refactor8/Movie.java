package refactor_code.part1.refactor8;

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

    /**
     * 重构移动方法 职责单一
     * @param daysRented
     * @return
     */
    public double getChange(int daysRented) {
        double result = 0;
        switch (get_priceCode()) {
            case refactor_code.part1.original.Movie.REGULAR:
                result += 2;
                if (daysRented > 2) {
                    result += (daysRented - 2) * 1.5;
                }
                break;
            case refactor_code.part1.original.Movie.NEW_RELEASE:
                result += daysRented * 3;
                break;
            case refactor_code.part1.original.Movie.CHILDRENS:
                result += 1.5;
                if (daysRented > 3) {
                    result += (daysRented - 3) * 1.5;
                }
                break;
        }
        return result;
    }


    /**
     * 重构移动方法 职责单一
     * @param daysRented
     * @return
     */
    public int getFrequentRenterPoints(int daysRented) {
        if (get_priceCode() == Movie.NEW_RELEASE && daysRented > 1) {
            return 2;
        } else {
            return 1;
        }
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
