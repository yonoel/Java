package explicitLock;

import avoidDangerous.Account;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

public class NewTransferMoney {
    public boolean transferMoney(Account from, Account to, double amount, long timeout, TimeUnit timeUnit) throws InterruptedException {
        long stopTime = System.nanoTime() + timeUnit.toNanos(timeout);
        while (true) {
            if (from.lock.tryLock()) {
                try {
                    if (to.lock.tryLock()) {
                        try {
                            if (from.getBalance() - amount < 0) {
                                throw new IllegalArgumentException();
                            } else {
                                from.debit(amount);
                                to.credit(amount);
                                return true;
                            }
                        } finally {
                            to.lock.unlock();
                        }

                    }
                } finally {
                    from.lock.unlock();
                }

            }
            if (System.nanoTime() < stopTime)
                return false;
            NANOSECONDS.sleep(1000);
        }

    }
}
