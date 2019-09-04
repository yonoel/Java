package concurrentCollection;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class HashFiller implements Runnable {
    public static void main(String[] args) throws Exception {
        ConcurrentHashMap<String, ConcurrentLinkedDeque<Operation>> userHash = new ConcurrentHashMap<>();

        HashFiller hashFiller = new HashFiller(userHash);

        Thread[] threads = new Thread[10];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(hashFiller);
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

        System.out.printf("size %d %n", userHash.size());

        userHash.forEach(10, (user, list) -> {
            System.out.printf("%s %s %d %n", Thread.currentThread().getName(), user, list.size());
        });

        userHash.forEachEntry(10, entry -> {
            System.out.printf("%s %s %d %n", Thread.currentThread().getName(), entry.getKey(), entry.getValue().size());
        });

        Operation search = userHash.search(10, (user, list) -> {
            for (Operation operation : list) {
                if (operation.getOperation().endsWith("1"))
                    return operation;
            }
            return null;
        });

        System.out.printf(" we have found id %s %s %s %n", search.getUser(), search.getOperation(), search.getTime());

        Integer reduce = userHash.reduce(10, (user, list) -> {
            return list.size();
        }, (n1, n2) -> n1 + n2);
        System.out.printf("total is " + reduce);
    }

    private ConcurrentHashMap<String, ConcurrentLinkedDeque<Operation>> userHash;

    public HashFiller(ConcurrentHashMap<String, ConcurrentLinkedDeque<Operation>> userHash) {
        this.userHash = userHash;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            Operation operation = new Operation();
            String user = "USER" + random.nextInt(100);
            String action = "OP" + random.nextInt(10);
            operation.setUser(user);
            operation.setOperation(action);
            operation.setTime(new Date());

            addOperationToHash(operation);
        }
    }

    private void addOperationToHash(Operation operation) {
        ConcurrentLinkedDeque<Operation> operations = userHash.computeIfAbsent(operation.getUser(), user -> new ConcurrentLinkedDeque<>());
        operations.add(operation);

    }
}
