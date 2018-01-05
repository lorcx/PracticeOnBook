package java_concurrency_in_pratice.part3;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 * 使用指向不可变对象的volitile类型引用以缓存最新结果
 * @Author lx
 * @Date 2018/1/5 21:35
 */
public class VolatileCachedFactorizer implements Servlet{
    private volatile OneValueCache cache = new OneValueCache(null, null);

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = cache.getFactors(i);
        if (factors == null) {
            factors = factor(i);
            cache = new OneValueCache(i, factors);
        }
        encodeIntoResponse(resp, factors);
    }

    private void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {

    }

    private BigInteger[] factor(BigInteger i) {
        return new BigInteger[0];
    }

    private BigInteger extractFromRequest(ServletRequest req) {
        return null;
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }

}
