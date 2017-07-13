package refactor_code.part1.refactor8;

import refactor_code.part1.refactor8.Movie;

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
     * 利用多态 switch用在基本属性应该用在所属对象上
     * srp原则
     * @return
     */
    public double getChange() {
        return movie.getChange(daysRented);
    }

    /**
     * 获取常客积分
     * @return
     */
    public int getFrequentRenterPoints() {
       return movie.getFrequentRenterPoints(daysRented);
    }

    public Movie getMovie() {
        return movie;
    }

    public int getDaysRented() {
        return daysRented;
    }
}
