package threadSynchronizedTool;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class NumberListGenerator implements Supplier<List<Long>> {
    private final int size;

    public NumberListGenerator(int size) {
        this.size = size;
    }

    @Override
    public List<Long> get() {
        List<Long> ret = new ArrayList<>();
        System.out.printf("%s : NumberListGenerator : start %n", Thread.currentThread().getName());
        for (int i = 0; i < size * 1000000; i++) {
            long number = Math.round(Math.random() * Long.MAX_VALUE);
            ret.add(number);
        }
        System.out.printf("%s : NumberListGenerator : end %n", Thread.currentThread().getName());
        return ret;
    }
}
