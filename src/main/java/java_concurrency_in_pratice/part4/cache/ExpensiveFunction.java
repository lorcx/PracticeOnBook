package java_concurrency_in_pratice.part4.cache;

/**
 * 模拟需要高昂代价的函数
 * @Author: lx
 * @Date: Created in 2018/1/8 0008
 */
public class ExpensiveFunction implements Computable<String, Integer> {
    @Override
    public Integer computable(String arg) throws InterruptedException {
        // 经过长时间处理后
        return new Integer(arg);
    }
}
