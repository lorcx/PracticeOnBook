package java_concurrency_in_pratice.part10;

/**
 * 通过锁顺序避免死锁
 * @Author lx
 * @Date 2018/2/11 11:02
 */
public class InduceLockOrder {
    // 如果hashCode相等则使用“加时锁”
    private static final Object tieLock = new Object();

    public void transferMoney(final Account fromAcct, final Account toAcct, final DollarAmount amount) throws InsufficientFundsException {
        class Helper {
            public void transfer() throws InsufficientFundsException {
                if (fromAcct.getBalance().compareTo(amount) < 0) {
                    throw new InsufficientFundsException();
                } else {
                    fromAcct.debit(amount);
                    toAcct.credit(amount);
                }
            }
        }

        // 获取对象的hashCode
        int fromHash = System.identityHashCode(fromAcct);
        int toHash = System.identityHashCode(toAcct);

        if (fromHash < toHash) {
            synchronized (fromAcct) {
                synchronized (toAcct) {
                    new Helper().transfer();
                }
            }
        } else if (toHash < fromHash) {
            synchronized (toAcct) {
                synchronized (fromAcct) {
                    new Helper().transfer();
                }
            }
        } else {
            synchronized (tieLock) {
                synchronized (toAcct) {
                    synchronized (fromAcct) {
                        new Helper().transfer();
                    }
                }
            }
        }
    }
}
