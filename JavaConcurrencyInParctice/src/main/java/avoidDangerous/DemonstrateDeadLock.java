package avoidDangerous;

import java.util.Random;

public class DemonstrateDeadLock {
    private static final int NUM_THREADS = 20;
    private static final int NUM_ACCOUNTS = 5;
    private static final int NUM_INTERATIONS = 1000000;

    public static void transferMoney(Account from, Account to, double amount) {

        synchronized (from) {
            synchronized (to) {

                if (from.getBalance() - amount < 0) {
                    throw new IllegalArgumentException("not enough money");
                } else {
                    from.debit(amount);
                    to.credit(amount);
                }
            }

        }

    }

    public static void main(String[] args) {
        final Random random = new Random();
        final Account[] accounts = new Account[NUM_ACCOUNTS];

        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account();
        }

        class TransferThread extends Thread {
            @Override
            public void run() {
                for (int i = 0; i < NUM_THREADS; i++) {
                    int fromAcct = random.nextInt(NUM_ACCOUNTS);
                    int toAcct = random.nextInt(NUM_ACCOUNTS);
                    double amount = random.nextDouble();
                    transferMoney(accounts[fromAcct], accounts[toAcct], amount);
                }
            }
        }

        for (int i = 0; i < NUM_THREADS; i++) {
            new TransferThread().start();
        }
    }
}
