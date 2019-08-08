package assemblyObject;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashSet;
import java.util.Set;

@ThreadSafe
public class PersonSet {
    @GuardedBy("this")
    private final Set<String> mySet = new HashSet<>();

    public synchronized void addPerson(String person){
        mySet.add(person);
    }

    public synchronized boolean containsPerson(String person){
        return mySet.contains(person);
    }
}
