package refactor_code.part5.extract_method.orginal;

/**
 * Created by Administrator on 2017/7/20 0020.
 */
public class Original {

    private String name;

    void printOwing(double amount) {
        printBanner();

        System.out.println("name: " + name);
        System.out.println("amount: " + amount);
    }

    private void printBanner() {

    }
}
