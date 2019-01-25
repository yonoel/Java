import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TESTHASHMAPCACHE {
    public static void main(String[] args) {
        Long start = Runtime.getRuntime().freeMemory();
        System.out.println("运行开始时内存"+start);
        List list = new ArrayList(10000);
        for (int i = 0; i < 100000; i++) {
//            User map = new User();
            HashMap<String,Object> map = new HashMap<>(2);
            map.put("id",12345L);
            map.put("name","map1");
//            map.setId(12345L);
//            map.setName("map1");
            list.add(map);
        }

        Long end = Runtime.getRuntime().freeMemory();
        System.out.println("程序执行消耗的内存:"+(start-end));
    }
}
