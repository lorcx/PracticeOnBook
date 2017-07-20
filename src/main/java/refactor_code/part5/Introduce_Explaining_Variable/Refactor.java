package refactor_code.part5.Introduce_Explaining_Variable;

/**
 * Created by Administrator on 2017/7/20 0020.
 */
public class Refactor {
    String platform = "";
    String browser = "";
    int resize = 0;

    // 将该复杂表达式（或其中一部分）的结果放进一个临时变量，以此变量名称来解释表达式用途
    void f() {
        final boolean isMacOs = platform.toUpperCase().indexOf("MAC") > -1;
        final boolean isIEBrowser = browser.toUpperCase().indexOf("IE") > -1;
        final boolean wasResized = resize > 0;
        if (isMacOs && isIEBrowser && wasInitialized() && wasResized) {
            // do something
        }

    }

    private boolean wasInitialized() {
        return false;
    }
}
