package Collections;

import java.util.HashSet;
import java.util.Set;

public class TestSet {
    public static void main(String[] args) {
        Set set = new HashSet(20);
        set.add('a');
        set.add('b');
        Character[] a = (Character[]) set.toArray(new Character[0]);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
