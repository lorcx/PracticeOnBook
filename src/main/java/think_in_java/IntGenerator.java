package think_in_java;

/**
 * 线程中通用实例的抽象类
 * 泛型不支持基本类型
 * Created by dell on 2016/2/4.
 */
@SuppressWarnings("all")
public abstract class IntGenerator {

    private volatile boolean canceled = false;//取消状态位

    public abstract int next();

    public void cancel(){
        canceled = true;
    }

    public boolean isCancel(){
        return canceled;
    }

}
