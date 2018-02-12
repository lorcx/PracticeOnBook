package java_concurrency_in_pratice.part14;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * Avoidance 躲避
 * delay 延迟
 * debit 借方
 * credit 信用
 * balance 平衡
 * Insufficient 不足
 * Funds 资金
 *
 * @Author lx
 * @Date 2018/2/12 21:13
 */
public class DeadLockAvoidance {
    private static Random rnd = new Random();
    private final static int DELAY_FIXED = 1;
    private final static int DELAY_RANDOM = 2;

    public boolean transferMoney(Account fromAcct, Account toAcct, DollarAmount amount, long timeout, TimeUnit unit) throws InsufficientFundsException, InterruptedException {
        long fixedDelay = getFixedDelayComponentNanos(timeout, unit);
        long randMod = getRandomDelayModulusNanos(timeout, unit);
        long stopTime = System.nanoTime() + unit.toNanos(timeout);

        while (true) {
            if (fromAcct.lock.tryLock()) {
                try {
                    if (toAcct.lock.tryLock()) {
                        try {
                            if (fromAcct.getBalance().compareTo(amount) < 0) {
                                throw new InsufficientFundsException();
                            } else {
                                fromAcct.debit(amount);
                                toAcct.credit(amount);
                                return true;
                            }
                        } finally {
                            toAcct.lock.unlock();
                        }
                    }
                } finally {
                    fromAcct.lock.unlock();
                }
            }

            if (System.nanoTime() < stopTime) {
                return false;
            }

            TimeUnit.NANOSECONDS.sleep(fixedDelay + rnd.nextLong() % randMod);
        }
    }

    private long getRandomDelayModulusNanos(long timeout, TimeUnit unit) {
        return DELAY_RANDOM;
    }

    private long getFixedDelayComponentNanos(long timeout, TimeUnit unit) {
        return DELAY_FIXED;
    }


    class Account {
        public Lock lock;

        void debit(DollarAmount d) {

        }

        void credit(DollarAmount d) {

        }

        DollarAmount getBalance() {
            return null;
        }
    }

    class InsufficientFundsException extends Exception {

    }

    static class DollarAmount implements Comparable<DollarAmount> {

        @Override
        public int compareTo(DollarAmount o) {
            return 0;
        }

        DollarAmount(int dollars) {

        }
    }
}
