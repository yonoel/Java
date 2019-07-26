package Collections;

import OOP.Child;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
         Stream<Child> stream = Stream.of(new Child(1L,"a"),new Child(2L,"b"));

//        stream.collect(new HashMap<String,Long>(),);
        BiConsumer<ArrayList<Child>, Child> accumulator = (r1,r2)->{
            r1.add(r2);
        };
        BiConsumer<ArrayList<Child>, ArrayList<Child>> combiner = (r1,r2)->{
            r1.addAll(r2);
        };

        stream.collect(()->new ArrayList<>(),accumulator,combiner);
        stream.collect(()->new HashMap<String,Long>(),(r1,r2)->{
            r1.put(r2.getName(),r2.getId());
        },(r1,r2)->{
            r1.putAll(r2);
        });

    }
}
