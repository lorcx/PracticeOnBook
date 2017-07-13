package refactor_code.part1.refactor7;

import refactor_code.part1.original.Movie;

/**
 * 租赁
 * Created by Administrator on 2017/7/13 0013.
 */
public class Rental {
    private Movie movie;
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    /**
     * 重构 移动方法
     * @param aRental
     * @return
     */
    public double getChange() {
        double result = 0;
        switch (getMovie().get_priceCode()) {
            case Movie.REGULAR:
                result += 2;
                if (getDaysRented() > 2) {
                    result += (getDaysRented() - 2) * 1.5;
                }
                break;
            case Movie.NEW_RELEASE:
                result += getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                result += 1.5;
                if (getDaysRented() > 3) {
                    result += (getDaysRented() - 3) * 1.5;
                }
                break;
        }
        return result;
    }

    /**
     * 获取常客积分
     * @return
     */
    public int getFrequentRenterPoints() {
        if (getMovie().get_priceCode() == Movie.NEW_RELEASE && getDaysRented() > 1) {
            return 2;
        } else {
            return 1;
        }
    }



    public Movie getMovie() {
        return movie;
    }

    public int getDaysRented() {
        return daysRented;
    }
}
