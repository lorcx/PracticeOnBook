package refactor_code.part1.refactor9;

/**
 * Created by Administrator on 2017/7/14 0014.
 */
public class NewReleasePrice extends Price {
    @Override
    int getPriceCode() {
        return Movie.NEW_RELEASE;
    }

    /**
     * 重构 拆分switch
     * @return
     */
    @Override
    double getChange(int daysRented) {
        return daysRented * 3;
    }

    /**
     * 重构拆分
     * @param daysRented
     * @return
     */
    @Override
    public int getFrequentRenterPoints(int daysRented) {
        return  daysRented > 1 ? 2 : 1;
    }
}
