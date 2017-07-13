package refactor_code.part1.refactor6;

import refactor_code.part1.original.Movie;
import refactor_code.part1.refactor6.Rental;

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
            Rental each = (Rental) rentals.nextElement();
            frequentRenterPoints += each.getFrequentRenterPoints();
            result += "\t" + each.getMovie().get_title() + "\t" + String.valueOf(each.getChange()) + "\n";
            totalAmount += each.getChange();
        }
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + "Frequent renter points";
        return result;
    }
}
