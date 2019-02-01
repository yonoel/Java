import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class TestSearch {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>(300000);
        for (int i = 0; i < 300000; i++) {
            arrayList.add(i);
        }
//        Collections.shuffle(arrayList);
        ArrayList<Integer> b = new ArrayList<>(100000);
        for (int i = 100000; i < 200000; i++) {
            b.add(i);
        }
//        Collections.shuffle(b);
        Long start = System.currentTimeMillis();
        int count = 0;
        for (int i = 0;  i< b.size(); i++) {
            for (int j = 0; j < arrayList.size(); j++) {
                if(b.get(i).equals(arrayList.get(j))){
                    count++;
                    break ;
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(" time is "+(end-start)+"; count is "+count);
    }
}
