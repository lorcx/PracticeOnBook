package refactor_code.part5.extract_method.refactor1;

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
        Enumeration e = orders.elements();
        double outstanding = 0.0;

        printBanner();

        // calculate outstanding
        while (e.hasMoreElements()) {
            Order each = (Order) e.nextElement();
            outstanding += each.getAmount();
        }

        // 重构1
        printDetails(outstanding);
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
