package refactor_code.part1.refactor9;

/**
 * 价格
 * Created by Administrator on 2017/7/14 0014.
 */
public abstract class Price {
    abstract int getPriceCode();

    /**
     * 重构移动方法 职责单一
     *
     * @param daysRented
     * @return
     */
    abstract double getChange(int daysRented);


    /**
     * 重构移动方法 职责单一
     *
     * @param daysRented
     * @return
     */
    public int getFrequentRenterPoints(int daysRented) {
        return 1;
    }

}