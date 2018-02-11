package java_concurrency_in_pratice.part10;

/**
 * 动态的锁顺序死锁
 * @Author lx
 * @Date 2018/2/11 10:44
 */
public class DynamicOrderDeadLock {
    /**
     * 账户a -> 账户b
     * 账户b -> 账户a
     * 会导致加锁顺序不一致，引发死锁
     * @param fromAccount
     * @param toAccount
     * @param amount
     * @throws InsufficientFundsException
     */
    public static void transferMoney(Account fromAccount, Account toAccount, DollarAmount amount) throws InsufficientFundsException {
        synchronized (fromAccount) {
            synchronized (toAccount) {
                if (fromAccount.getBalance().compareTo(amount) < 0) {
                    throw new InsufficientFundsException();
                } else {
                    fromAccount.debit(amount);
                    toAccount.credit(amount);
                }
            }
        }
    }
}
