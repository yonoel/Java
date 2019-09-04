package customConcurrent;

import java.util.Date;

public class MyThread extends Thread {
    private final Date creationDate;
    private Date startDate;
    private Date finishDate;

    public MyThread(Runnable target, String name) {
        super(target, name);
        creationDate = new Date();
    }

    @Override
    public void run() {
        setStartDate();
        super.run();
        setFinishDate();
    }

    public synchronized void setStartDate() {
        this.startDate = new Date();
    }

    public synchronized void setFinishDate() {
        this.finishDate = new Date();
    }

    public synchronized long getExecutionTime() {
        return finishDate.getTime() - startDate.getTime();
    }

    @Override
    public synchronized String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getName())
                .append(": creation date :").append(creationDate)
                .append(" : running time :").append(getExecutionTime());
        return stringBuilder.toString();
    }
}
