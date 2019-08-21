package cancelAndShutDown;

import net.jcip.annotations.GuardedBy;
import org.springframework.context.annotation.Primary;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;


public class PrimeGenerator implements Runnable {
    public static void main(String[] args) throws InterruptedException{
        PrimeGenerator generator = new PrimeGenerator();
        new Thread(generator).start();
        try {
            SECONDS.sleep(1);
        } finally {
            generator.cancel();
        }
        System.out.println(generator.getPrimes());
    }
    private volatile boolean cancelled;
    @GuardedBy("this")
    private final List<BigInteger> primes = new ArrayList<>();

    @Override
    public void run() {
        BigInteger one = BigInteger.ONE;
        while (!cancelled){
            one = one.nextProbablePrime();
            synchronized (this){
                primes.add(one);
            }
        }
    }

    public void cancel(){
        cancelled = true;
    }

    public synchronized List<BigInteger> getPrimes() {
        return new ArrayList<>(primes);
    }
}
