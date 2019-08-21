package avoidDangerous;

import java.util.concurrent.locks.Lock;

public class Account {
    public Lock lock;
    double balance;

    public double getBalance() {
        return balance;
    }

    public void debit(double amount) {
        balance -= amount;
    }

    public void credit(double amount) {
        balance += amount;
    }

}
