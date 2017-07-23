package java_structure_arithmetic;

/**
 *  栈应用：检查分割符是否匹配
 *
 *  如果找到开始分隔符 就入栈 ，如果找到结束分隔符就出栈
 *  如果出栈的的字符和当前字符不匹配 那么就错误
 *  利用了后进先出
 * Created by lx on 2016/8/20.
 */
public class BracketChecker1 {
    private StackX<Character> stackX;
    //    private String testStr = "a{b(c{d]e)f}";
    private String testStr = "a{";

    /**
     * 检查
     */
    public void check() {
        int stackSize = testStr.length();
        stackX = new StackX<>(stackSize);
        for (int i = 0; i < stackSize; i++) {
            char ch = testStr.charAt(i);
            switch (ch) {
                //遇到开始符号就放到栈中
                case '{':
                case '(':
                case '[':
                    stackX.push(ch);
                    break;
                //结束字符
                case '}':
                case ')':
                case ']':
                    if(!stackX.isEmpty()) {
                        char chx = stackX.pop();
                        if(ch == '}' && chx != '{' ||
                                ch == ']' && chx != '[' ||
                                ch == ')' && chx != '(' ) {
                            System.out.println("错误：" + ch + " 在 " + i);
                        }
                    } else {
                        System.out.println("错误：" + ch + " 在 " + i);
                    }
                    break;
                default:
                    break;
            }
        }
        if (!stackX.isEmpty()) {
            System.out.println("错误：缺少分割符");
        }
    }

    public static void main(String[] args) {
        new BracketChecker1().check();
    }
}

