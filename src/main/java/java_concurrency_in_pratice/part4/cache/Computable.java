package java_concurrency_in_pratice.part4.cache;

/**
 * 计算接口
 * @Author: lx
 * @Date: Created in 2018/1/8 0008
 */
public interface Computable<A, V> {
    V computable(A arg) throws InterruptedException;
}


