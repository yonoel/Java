package buildBasicModule;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Memorizer<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> computable;

    public Memorizer(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(A args) throws InterruptedException {
        Future<V> result = cache.get(args);
        if (result == null) {
            FutureTask<V> futureTask = new FutureTask<V>(() -> {
                return computable.compute(args);
            });

            result = cache.putIfAbsent(args, futureTask);
            if (result == null){
                result = futureTask;
                futureTask.run();
            }
        }
        try {
            return result.get();
        } catch (ExecutionException e) {
//            throw (Throwable) e;
            throw new InterruptedException();
            // 不高兴写异常实现类了。
        }
    }
}
