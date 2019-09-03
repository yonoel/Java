package Collections;

import sun.text.resources.CollationData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestArrayList {
    public static void main(String[] args) {
        List list = IntStream.range(0, 100).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println(list.get(99));
    }
}
