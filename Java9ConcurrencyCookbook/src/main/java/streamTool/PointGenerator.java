package streamTool;

import java.util.*;
import java.util.stream.DoubleStream;

public class PointGenerator {
    public static void main(String[] args) {
//        List<Double> doubleList = DoubleGenerator.generateDoubleList(10_000, 1000);
//        DoubleStream doubleStream = DoubleGenerator.generateDoubleStream(doubleList);
//        // 流只能用一次结尾就结束了
//        double average = doubleStream.parallel().average().getAsDouble();
//        System.out.println(average + " ------average ");

        List<Point> points = PointGenerator.generatePointList(10_000);

        Optional<Point> optionalPoint = points.parallelStream().reduce((p1, p2) -> {
            Point point = new Point();
            point.setX(p1.getX() + p2.getX());
            point.setY(p1.getY() + p2.getY());
            return point;
        });
        // 归约成一个point，x，y为其和
        System.out.println(optionalPoint.get().getX() + " : " + optionalPoint.get().getY());

        List<Person> personList = PersonGenerator.generatePersonList(10_0000);

        Integer sum = personList.parallelStream().map(Person::getSalary)
                .reduce(0, (s1, s2) -> s1 + s2);
        System.out.println(sum + "第二个版本的reduce");

        int value = 0;
        Integer sum2 = personList.parallelStream().
                reduce(value, (n, p2) -> {
                    if (p2.getSalary() > 50_000) return n + 1;
                    else return n;
                }, (n1, n2) -> n1 + n2);
        ArrayList<Person> reducer = new ArrayList<>();
        ArrayList<Person> reduce = personList.parallelStream().reduce(
                reducer
                , (map, person) -> {
                    if (person.getSalary() > 50_0000) {
                        map.add(person);
                    }
                    ;
                    return map;
                }, (r1, r2) -> {
                    r1.addAll(r2);
                    return r1;
                }
        );
        System.out.println(sum2+"第三个版本的reduce，当返回值类型和流当元素不同时，可以这么做");
    }

    public static List<Point> generatePointList(int size) {
        Random random = new Random();
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Point point = new Point();
            point.setX(random.nextDouble());
            point.setY(random.nextDouble());
            points.add(point);
        }
        return points;
    }
}
