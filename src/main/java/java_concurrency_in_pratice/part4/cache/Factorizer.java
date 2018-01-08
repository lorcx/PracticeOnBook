package java_concurrency_in_pratice.part4.cache;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 * @Author: lx
 * @Date: Created in 2018/1/8 0008
 */
public class Factorizer extends GenericServlet implements Servlet {
    private final Computable<BigInteger, BigInteger[]> c = new Computable<BigInteger, BigInteger[]>() {
        @Override
        public BigInteger[] computable(BigInteger arg) throws InterruptedException {
            return factor(arg);
        }

        private BigInteger[] factor(BigInteger arg) {
            return null;
        }
    };


    private final Computable<BigInteger, BigInteger[]> cache = new Memoizer4<>(c);

    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        try {
            encodeInfoResponse(resp, cache.computable(i));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void encodeInfoResponse(ServletResponse resp, BigInteger[] computable) {

    }

    private BigInteger extractFromRequest(ServletRequest req) {
        return null;
    }
}
