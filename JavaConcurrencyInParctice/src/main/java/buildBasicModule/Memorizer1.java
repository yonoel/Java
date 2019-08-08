package buildBasicModule;

import net.jcip.annotations.GuardedBy;

import java.util.HashMap;
import java.util.Map;

public class Memorizer1<A, V> implements Computable<A, V> {
    @GuardedBy("this")
    private final Map<A, V> cache = new HashMap<A, V>();
    private final Computable<A, V> computable;

    public Memorizer1(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public synchronized V compute(A args) throws InterruptedException {
        V result = cache.get(args);
        if (result == null) {
            result = computable.compute(args);
            cache.put(args, result);
        }
        return result;
    }
}
