package concurrentCollection;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.concurrent.atomic.LongAdder;

public class Account {
    public static void main(String[] args) {
        Account account = new Account();
        account.setBalance(1000);
        Company company = new Company(account);

        Thread cThread = new Thread(company);

        Bank bank = new Bank(account);
        Thread bThread = new Thread(bank);

        System.out.println("account initial balance is " + account.getBalance());

        cThread.start();
        bThread.start();

        try {
            cThread.join();
            bThread.join();
            System.out.println("final balance is " + account.getBalance());
            System.out.println("operation size is " + account.getOperations());
            System.out.println("accumulated commission is " + account.getCommission());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private final AtomicLong balance;
    private final LongAdder operation;
    private final DoubleAccumulator commission;

    public Account() {
        balance = new AtomicLong(0);
        operation = new LongAdder();
        commission = new DoubleAccumulator((x, y) -> x + y * 0.2, 0);
    }

    public long getBalance() {
        return balance.get();
    }

    public long getOperations() {
        return operation.longValue();
    }

    public double getCommission() {
        return commission.get();
    }

    public void setBalance(long balance) {
        this.balance.set(balance);
        operation.reset();
        commission.reset();
    }

    public void addAmount(long amount) {
        this.balance.getAndAdd(amount);
        this.operation.increment();
        this.commission.accumulate(amount);
    }

    public void subtractAmount(long amount) {
        this.balance.getAndAdd(-amount);
        this.operation.increment();
        this.commission.accumulate(amount);
    }
}
