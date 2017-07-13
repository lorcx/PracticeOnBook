package refactor_code.part1.refactor9;

/**
 * Created by Administrator on 2017/7/14 0014.
 */
public class RegularPrice extends Price {
    @Override
    int getPriceCode() {
        return Movie.REGULAR;
    }

    /**
     * 重构 拆分switch
     * @param daysRented
     * @return
     */
    @Override
    double getChange(int daysRented) {
        double result = 2;
        if (daysRented > 2) {
            result += (daysRented - 2) * 1.5;
        }
        return result;
    }


}
