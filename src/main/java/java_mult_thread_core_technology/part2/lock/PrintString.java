package java_mult_thread_core_technology.part2.lock;

/**
 * Created by Administrator on 2017/8/22.
 */
public class PrintString {
    private boolean isContinuePrint = true;

    public boolean isContinuePrint() {
        return isContinuePrint;
    }

    public void setContinuePrint(boolean isContinuePrint) {
        this.isContinuePrint = isContinuePrint;
        System.out.println(isContinuePrint);
    }

    public void printStringMethod() {
        try {
            while (isContinuePrint) {
                System.out.println("run printStringMethod threadName=" + Thread.currentThread().getName());
                System.out.println(isContinuePrint);
                Thread.sleep(3000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Run17 {
    public static void main(String[] args) {
        PrintString ps = new PrintString();
        ps.printStringMethod();
        System.out.println("stop");
        ps.setContinuePrint(false);
    }
}