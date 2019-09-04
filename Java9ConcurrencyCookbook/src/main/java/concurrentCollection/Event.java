package concurrentCollection;

public class Event implements Comparable<Event> {
    private final int thread;
    private final int priority;

    public Event(int id, int i) {
        this.thread = id;
        this.priority = i;
    }

    public int getThread() {
        return thread;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Event o) {
        if (this.priority > o.getPriority()) {
            return -1;
        } else if (this.priority < o.getPriority()) {
            return 1;
        }
        return 0;
    }
}
