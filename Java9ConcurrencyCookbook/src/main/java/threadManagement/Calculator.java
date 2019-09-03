package threadManagement;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Calculator implements Runnable {

    @Override
    public void run() {
        long current = 1L;
        long max = 20000L;
        long numPrimes = 0L;
        System.out.printf("Thread '%s': START %n", Thread.currentThread().getName());
        while (current <= max) {
            if (isPrime(current)) {
                numPrimes++;
            }
            current++;
        }
        System.out.printf("Thread '%s': END. Number of primes : %d %n", Thread.currentThread().getName(), numPrimes);
    }

    public static boolean isPrime(long num) {
        if (num <= 2) return true;
        long l = num / 2 + 1;
        for (long i = 2; i < l; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
