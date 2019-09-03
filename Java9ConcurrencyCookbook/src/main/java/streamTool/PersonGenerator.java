package streamTool;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class PersonGenerator {
    public static void main(String[] args) {
        List<Person> people = PersonGenerator.generatePersonList(1000);
        ConcurrentHashMap<String, Counter> collect = people.parallelStream().collect(() -> new ConcurrentHashMap<String, Counter>(),
                (map, person) -> {
                    map.computeIfPresent(person.getFirstName(), (name, counter) -> {
                        counter.increment();
                        return counter;
                    });
                    map.computeIfAbsent(person.getFirstName(), name -> {
                        Counter counter = new Counter();
                        counter.setValue(name);
                        return counter;
                    });
                }, (map1, map2) -> {
                    map2.forEach(10, (key, value) -> {
                        map1.merge(key, value, (v1, v2) -> {
                            v1.setCounter(v1.getCounter() + v2.getCounter());
                            return v1;
                        });
                    });
                });

        collect.forEach((key, value) -> {
            System.out.printf("%s: %d %n", key, value.getCounter());
        });

    }

    public static List<Person> generatePersonList(int size) {
        List<Person> ret = new ArrayList<>();
        String firstNames[] = {"Marry", "Patricia", "Linda", "Barbara", "Elizabeth", "James", "John", "Robert", "Michael", "William"};
        String lastNames[] = {"Smith", "Jones", "Taylor", "Brown", "Davies", "Evans", "Wilson", "Thomas", "Roberts", "Williams"};
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            Person person = new Person();
            person.setId(i);
//            person.setFirstName(firstNames[random.nextInt(10)]);
            person.setFirstName("Marry");
            person.setLastName(lastNames[random.nextInt(10)]);
            person.setSalary(random.nextInt(10_0000));
            person.setCoeficient(random.nextDouble() * 10);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, -random.nextInt(30));
            person.setBirthday(calendar.getTime());
            ret.add(person);
        }
        return ret;
    }
}
