package concurrentCollection;

import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class MapTask implements Runnable {
    public static void main(String[] args) throws Exception {
        ConcurrentSkipListMap<String, Contact> map = new ConcurrentSkipListMap<>();
        Thread[] threads = new Thread[26];
        int counter = 0;

        for (char i = 'A'; i <= 'Z'; i++) {
            MapTask task = new MapTask(map, String.valueOf(i));
            threads[counter] = new Thread(task);
            threads[counter].start();
            counter++;
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Main the map size is " + map.size());
        Map.Entry<String, Contact> ele;
        Contact contact;

        ele = map.firstEntry();
        contact = ele.getValue();
        System.out.println("Main first ele " + contact.getName() + " : " + contact.getPhone());

        ele = map.lastEntry();
        contact = ele.getValue();
        System.out.println("Main last ele " + contact.getName() + " : " + contact.getPhone());

        ConcurrentNavigableMap<String, Contact> subMap = map.subMap("A1996", "B1002");
        subMap.forEach((key, value) -> {
            System.out.printf(" %s : %s %n", value.getName(), value.getPhone());
        });
    }

    private final ConcurrentSkipListMap<String, Contact> map;
    private final String id;

    public MapTask(ConcurrentSkipListMap<String, Contact> map, String id) {
        this.map = map;
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            Contact contact = new Contact(id, String.valueOf(i + 1000));
            map.put(id + contact.getPhone(), contact);
        }
    }
}
