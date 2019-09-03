package streamTool;

import java.util.Comparator;
import java.util.List;
import java.util.stream.DoubleStream;

public class ForEveryEle {
    public static void main(String[] args) {
        List<Person> people = PersonGenerator.generatePersonList(10);
        people.forEach(System.out::println);
        System.out.println("***************");
        people.stream().sorted().forEachOrdered(System.out::println);
        List<Double> doubles = DoubleGenerator.generateDoubleList(10, 100);
        DoubleStream doubleStream = DoubleGenerator.generateDoubleStream(doubles);
        doubleStream.peek(d-> System.out.printf("Step 1: Number %f%n",d))
        .peek(d-> System.out.printf("Step 2: Number %f%n",d))
        .forEach(d-> System.out.printf("Step 3: Number %f%n",d));
    }
}
