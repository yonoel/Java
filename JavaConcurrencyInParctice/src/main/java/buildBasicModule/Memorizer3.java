package buildBasicModule;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Memorizer3<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> computable;

    public Memorizer3(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(A args) throws InterruptedException {
        Future<V> result = cache.get(args);
        if (result == null) {
            //唯一缺点就是这一块可能出现并发
            FutureTask<V> futureTask = new FutureTask<V>(() -> {
                return computable.compute(args);
            });

            cache.put(args, futureTask);
            futureTask.run();
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
