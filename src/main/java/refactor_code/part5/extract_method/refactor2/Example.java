package refactor_code.part5.extract_method.refactor2;

import refactor_code.part5.extract_method.orginal.Order;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by Administrator on 2017/7/20 0020.
 */
public class Example {
    private String name;
    private Vector orders = new Vector();

    void printOwing(double amount) {
        printBanner();
        // 重构2
        double outstanding = getOutstanding();
        // 重构1
        printDetails(outstanding);
    }

    private double getOutstanding() {
        Enumeration e = orders.elements();
        double result = 0.0;
        // calculate outstanding
        while (e.hasMoreElements()) {
            Order each = (Order) e.nextElement();
            result += each.getAmount();
        }
        return result;
    }

    private void printDetails(double outstanding) {
        // print details
        System.out.println("name: " + name);
        System.out.println("amount: " + outstanding);
    }

    private void printBanner() {
        System.out.println("***********************");
        System.out.println("*********Customer Owes*********");
        System.out.println("***********************");
    }
}
