package refactor_code.part1.refactor9;


/**
 * Created by Administrator on 2017/7/14 0014.
 */
public class ChildrensPrice extends Price{

    @Override
    int getPriceCode() {
        return Movie.CHILDRENS;
    }

    /**
     * 重构拆分switch
     * @param daysRented
     * @return
     */
    @Override
    double getChange(int daysRented) {
        double result = 1.5;
        if (daysRented > 3) {
            result += (daysRented - 3) * 1.5;
        }
        return result;
    }
}
