package refactor_code.part1.refactor9;

/**
 * Created by Administrator on 2017/7/13 0013.
 */
public class Movie {
    private String _title;
    private Price _priceCode;
    private int priceCode;
    public static final int CHILDRENS = 2;// 孩子
    public static final int REGULAR = 0;// 正规的
    public static final int NEW_RELEASE = 1;// 新发行的


    public Movie(String title, int priceCode) {
        this._title = title;
        setPriceCode(priceCode);
    }

    public void setPriceCode(int priceCode) {
        switch (priceCode) {
            case REGULAR:
                _priceCode = new RegularPrice();
                break;
            case CHILDRENS:
                _priceCode = new ChildrensPrice();
                break;
            case NEW_RELEASE:
                _priceCode = new NewReleasePrice();
                break;
            default:
                throw new IllegalArgumentException("Incorrect price code");
        }
    }



}
