package effective_java;

import java.util.Arrays;

/**
 * 内存溢出
 * 这个类自己管理内存
 *
 * 简单的栈实现
 * Created by dell on 2016/4/19.
 */
public class Stack {
    private Object[] element;
    private int size = 0;
    private static final int DEFAULT_CAPITLY = 16;//默认大小

    public Stack() {
        this(DEFAULT_CAPITLY);
    }

    public Stack(int len) {
        this.element = new Object[len];
    }

    public void put(Object obj){
        ensureCapitly();
        element[size++] = obj;
    }

    public Object offer(){
        if(size == 0){
            throw new NullPointerException();
        }
        //泄露的
//        return element[size--];//这个栈维护着过期的引用，所以垃圾回收器不会回收内存
        Object obj = element[--size];
        element[size] = null;
        return obj;
    }

    /**
     * 自动扩容
     */
    private void ensureCapitly(){
        if(element.length == size){
            Arrays.copyOf(element,size * 2 + 1);
        }
    }

    public void getValues(){
        while(true){
            try {
                System.out.println(offer());
            } catch (Exception e) {
                return;
            }
        }
    }

    @Override
    protected Stack clone() throws CloneNotSupportedException {
        Stack st = (Stack) super.clone();
        st.element = this.element.clone();//要克隆这个对象的数组
        return st;
    }

    public static void main(String[] args) {
        Stack st = new Stack();
        st.put("a");
        st.put("b");
        st.put("c");
        st.put("d");
        st.getValues();
    }
}
