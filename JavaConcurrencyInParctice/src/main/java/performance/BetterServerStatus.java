package performance;

import net.jcip.annotations.GuardedBy;

import java.util.Set;

public class BetterServerStatus {
    @GuardedBy("users")
    public final Set<String> users;
    @GuardedBy("queries")
    public final Set<String> queries;

    public BetterServerStatus(Set<String> users, Set<String> queries) {
        this.users = users;
        this.queries = queries;
    }

    public void addUser(String u) {
        synchronized (users) {
            users.add(u);
        }
    }

    public void addQuery(String u) {
        synchronized (queries) {
            queries.add(u);
        }
    }

    public void removeUser(String u) {
        synchronized (users) {
            users.remove(u);
        }
    }

    public void removeQuery(String u) {
        synchronized (queries) {
            queries.remove(u);
        }
    }
}
