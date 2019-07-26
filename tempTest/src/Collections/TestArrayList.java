package Collections;

import sun.text.resources.CollationData;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestArrayList {
    public static void main(String[] args) {
//        List<Integer> list = IntStream.range(0, 100).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
//        Function<String,List<Integer>> classifier = (a,v) ->{
//            return v == 10;
//        };
//        list.stream().collect(Collectors.groupingBy(classifier));
//        System.out.println(list.get(99));
        List<String> items = Arrays.asList("apple", "apple", "banana", "orange");
        Function<String, String> function = (a) -> {
            a += "1";
            return a;
        };
//        Map<String, List<String>> result = items.stream().collect(Collectors.groupingBy(function));
//        System.out.println(result);
//        items.stream().reduce(HashMap)
    }
}
