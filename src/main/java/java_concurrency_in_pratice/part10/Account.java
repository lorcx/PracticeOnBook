package java_concurrency_in_pratice.part10;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * debit 借方
 * credit 信用
 * balance 结算
 * @Author lx
 * @Date 2018/2/11 10:45
 */
public class Account {
    private DollarAmount balance;
    private final int acctNo;
    private static final AtomicInteger sequence = new AtomicInteger();

    public Account() {
        acctNo = sequence.incrementAndGet();
    }

    void debit(DollarAmount d) {
        balance = balance.subtract(d);
    }

    void credit(DollarAmount d) {
        balance = balance.add(d);
    }

    DollarAmount getBalance() {
        return balance;
    }

    int getAcctNo() {
        return acctNo;
    }
}
