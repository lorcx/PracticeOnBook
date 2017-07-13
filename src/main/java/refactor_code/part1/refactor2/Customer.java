package refactor_code.part1.refactor2;

import refactor_code.part1.original.Movie;
import refactor_code.part1.original.Rental;

import java.util.Enumeration;
import java.util.Vector;

/**
 * 顾客
 * Created by Administrator on 2017/7/13 0013.
 */
public class Customer {
    private String _name;
    private Vector _rentals = new Vector();

    public Customer(String _name) {
        this._name = _name;
    }

    /**
     * 添加租赁信息
     * @param arg
     */
    public void addRental(Rental arg) {
        _rentals.addElement(arg);
    }

    public String getName() {
        return _name;
    }

    /**
     * 要重构的方法
     * @return
     */
    public String statment() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration rentals = _rentals.elements();
        String result = "Rental Record for " + getName() + "\n";
        while (rentals.hasMoreElements()) {
            double thisAmount = 0;
            Rental each = (Rental) rentals.nextElement();

            // 拆分成方法
            thisAmount = amountFor(each);

            frequentRenterPoints++;
            if (each.getMovie().get_priceCode() == Movie.NEW_RELEASE
                    && each.getDaysRented() > 1) {
                frequentRenterPoints++;
                result += "\t" + each.getMovie().get_title() + "\t" +
                            String.valueOf(thisAmount) + "\n";
                totalAmount += thisAmount;
            }
        }

        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + "Frequent renter points";
        return result;
    }

    /**
     *  重构:修改局部变量名称
     * @param aRental
     * @return
     */
    private double amountFor(Rental aRental) {
        double result = 0;
        switch (aRental.getMovie().get_priceCode()) {
            case Movie.REGULAR:
                result += 2;
                if (aRental.getDaysRented() > 2) {
                    result += (aRental.getDaysRented() - 2) * 1.5;
                }
                break;
            case Movie.NEW_RELEASE:
                result += aRental.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                result += 1.5;
                if (aRental.getDaysRented() > 3) {
                    result += (aRental.getDaysRented() - 3) * 1.5;
                }
                break;
        }
        return result;
    }
}
