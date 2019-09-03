package threadExecutor;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class UserValidator {
    private final String name;

    public UserValidator(String name) {
        this.name = name;
    }

    public boolean vaildate(String name, String password) {
        Random random = new Random();
        try {
            long duration = (long) (Math.random() * 10);
            System.out.printf("Validator %s : validating a user during %d second %n", this.name, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return random.nextBoolean();
    }

    public String getName() {
        return name;
    }
}
