package sharedObject;

import java.util.HashSet;
import java.util.Set;

public class KnownSecrets {
    public static Set<String> knownSecrets;
    public void initialize(){
        knownSecrets = new HashSet<>();
    }
}
