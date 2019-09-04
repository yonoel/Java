package customConcurrent;

public class Event implements Comparable<Event> {
    private final String thread;
    private final int priority;

    public Event(String thread, int priority) {
        this.thread = thread;
        this.priority = priority;
    }

    public String getThread() {
        return thread;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Event o) {
        return Integer.compare(o.priority,this.priority);
    }
}
