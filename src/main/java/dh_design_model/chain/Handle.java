package dh_design_model.chain;

/**
 * Created by lx on 2016/6/19.
 */
public abstract class Handle {
    protected Handle successor;

    /**
     * 设置继承者
     * @param successor
     */
    public void setSuccessor(Handle successor) {
        this.successor = successor;
    }

    /**
     * 处理请求
     */
    public abstract void handleReuest(int request);
}
