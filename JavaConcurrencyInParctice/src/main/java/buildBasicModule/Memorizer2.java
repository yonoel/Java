package buildBasicModule;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Memorizer2<A,V> implements Computable<A,V>{
    private final Map<A, V> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> computable;

    public Memorizer2(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(A args) throws InterruptedException {
        V result = cache.get(args);
        if (result == null) {
            // 解决了并发访问问题，但是没有解决并发计算，耗时太久缺得到的值相同的问题
            result = computable.compute(args);
            cache.put(args, result);
        }
        return result;
    }
}
