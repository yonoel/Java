package sharedObject;

import net.jcip.annotations.Immutable;

import java.util.HashSet;
import java.util.Set;

@Immutable
public class ThreeStooges {

    private final Set<String> stooges = new HashSet<>();

    public ThreeStooges() {
        stooges.add("Moe");
        stooges.add("like");
        stooges.add("curly");
    }

    public boolean isStooge(String name) {
        return stooges.contains(name);
    }
}
