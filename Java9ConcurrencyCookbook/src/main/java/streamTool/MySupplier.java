package streamTool;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MySupplier implements Supplier<String> {
    public static void main(String[] args) {
        System.out.println("从集合构造流");
        List<Person> people = PersonGenerator.generatePersonList(1000);
        Stream<Person> personStream = people.parallelStream();
        System.out.println("****************");
        System.out.println("从supplier构造流");
        MySupplier mySupplier = new MySupplier();
        Stream<String> supplierStream = Stream.generate(mySupplier).limit(500);
        System.out.println("****************");
        System.out.println("从静态方法构造流");
        Stream<String> stringStream = Stream.of("peter", "john", "Mary");
        System.out.println("****************");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/yonoel/Desktop/java/Java/log.txt"))) {
            System.out.println("从文件流构造流");
            Stream<String> lines = bufferedReader.lines();
            System.out.println("****************");
            lines.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("从Files构造流");
            Stream<Path> pathStream = Files.list(Paths.get("/Users/yonoel/Desktop/"));
            pathStream.close();
            System.out.println("****************");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("从array构造流");
        int array[] = {1, 2, 3, 4, 5, 6};
        IntStream stream = Arrays.stream(array);
        System.out.println("****************");
        System.out.println("从random构造流");
        Random random = new Random();
        DoubleStream doubleStream = random.doubles();
        System.out.println("****************");
        System.out.println("合并流");
        Stream<Integer> concat = Stream.concat(Stream.of(1, 2, 3, 4), Stream.of(3, 4, 5, 6));
        System.out.println("****************");

    }

    private final AtomicInteger counter;

    public MySupplier() {
        counter = new AtomicInteger(0);
    }

    @Override
    public String get() {
        int increment = counter.getAndIncrement();
        return "String " + increment;
    }
}
