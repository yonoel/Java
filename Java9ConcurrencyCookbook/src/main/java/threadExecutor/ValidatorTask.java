package threadExecutor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ValidatorTask implements Callable<String> {
    public static void main(String[] args) {
        String username = "test";
        String password = "test";
        UserValidator ldap = new UserValidator("LDAP");
        UserValidator db = new UserValidator("DataBase");
        ValidatorTask ldapTask = new ValidatorTask(ldap, username, password);
        ValidatorTask dbTask = new ValidatorTask(db, username, password);
        List<ValidatorTask> validatorTasks = Arrays.asList(ldapTask, dbTask);
        ExecutorService service = Executors.newCachedThreadPool();

        String result;

        try {
            result = service.invokeAny(validatorTasks);
            System.out.printf("Main Result %s %n", result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        service.shutdown();
        System.out.println("Main end ");
    }

    private final UserValidator validator;
    private final String user;
    private final String password;

    public ValidatorTask(UserValidator validator, String user, String password) {
        this.validator = validator;
        this.user = user;
        this.password = password;
    }

    @Override
    public String call() throws Exception {
        if (!validator.vaildate(user, password)) {
            System.out.printf("%s :the user has not been found %n", validator.getName());
            throw new IllegalArgumentException("wrong name");
        }
        return validator.getName();
    }
}
