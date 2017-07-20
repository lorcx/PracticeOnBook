package refactor_code.part5.extract_method.refactor;

/**
 *  重构后
 * Created by Administrator on 2017/7/20 0020.
 */
public class Refactor {
    private String name;

    void printOwing(double amount) {
        printBanner();
        printDetil(amount);
    }

    private void printDetil(double amount) {
        System.out.println("name: " + name);
        System.out.println("amount: " + amount);
    }

    private void printBanner() {

    }
}
