package avoidDangerous;

public class TransferMoneyClass {
    public void transferMoney(Account from, Account to, double amount) {

        synchronized (from){
            synchronized (to){

                if (from.getBalance() - amount < 0) {
                    throw new IllegalArgumentException("not enough money");
                } else {
                    from.debit(amount);
                    to.credit(amount);
                }
            }

        }

    }


}
