package streamTool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.DoubleStream;

public class DoubleGenerator {
    public static List<Double> generateDoubleList(int size, int max) {
        Random random = new Random();
        List<Double> doubles = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            double v = random.nextDouble() * max;
            doubles.add(v);
        }
        return doubles;
    }

    public static DoubleStream generateDoubleStream(List<Double> doubles){
        DoubleStream.Builder builder = DoubleStream.builder();
        for (Double aDouble : doubles) {
            builder.add(aDouble);
        }
        return builder.build();
    }
}
