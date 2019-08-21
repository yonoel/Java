package avoidDangerous;

public class TransferMoneyClass2 {
    private static final Object tieLock = new Object();

    public void transferMoney(Account from, Account to, double amount) {
        class Helper {
            public void transfer() {
                if (from.getBalance() - amount < 0) {
                    throw new IllegalArgumentException("not enough money");
                } else {
                    from.debit(amount);
                    to.credit(amount);
                }
            }
        }

        int fromHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);
        if (fromHash < toHash){
            synchronized (from){
                synchronized (to){
                    new Helper().transfer();
                }
            }
        }else if (fromHash > toHash){
            synchronized (to){
                synchronized (from){
                    new Helper().transfer();
                }
            }
        }else {
            synchronized (tieLock){
                synchronized (from){
                    synchronized (to){
                        new Helper().transfer();
                    }
                }
            }
        }



    }

    class Account {
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
}
