package buildBasicModule;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ExpensiveFunction implements Computable<String, BigInteger> {
    @Override
    public BigInteger compute(String args) throws InterruptedException {
        // 耗时许久
        return new BigInteger(args);
    }
}
