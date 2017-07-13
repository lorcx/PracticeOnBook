package refactor_code.part1.refactor8;

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
        Enumeration rentals = _rentals.elements();
        String result = "Rental Record for " + getName() + "\n";
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            result += "\t" + each.getMovie().get_title() + "\t" + String.valueOf(each.getChange()) + "\n";
        }
        result += "Amount owed is " + String.valueOf(getTotalChange()) + "\n";
        result += "You earned " + String.valueOf(getTotalFrequentRenterPointer()) + "Frequent renter points";
        return result;
    }

    /**
     * 重构替换临时变量
     * @return
     */
    private double getTotalChange() {
        double result = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            result += each.getChange();
        }
        return result;
    }

    /**
     * 重构替换临时变量
     * @return
     */
    private double getTotalFrequentRenterPointer() {
        double result = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            result += each.getFrequentRenterPoints();
        }
        return result;
    }
}
