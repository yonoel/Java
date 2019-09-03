package threadManagement;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CalculatorMain {
    public static void main(String[] args) throws IOException {
        System.out.printf("Mininum Priority: %s %n", Thread.MIN_PRIORITY);
        System.out.printf("Normal Priority: %s %n", Thread.NORM_PRIORITY);
        System.out.printf("Maxinum Priority: %s %n", Thread.MAX_PRIORITY);

        int count = 10;
        Thread[] threads = new Thread[count];
        Thread.State[] states = new Thread.State[count];
        for (int i = 0; i < count; i++) {
            threads[i] = new Thread(new Calculator());
            Thread thread = threads[i];
            if (i % 2 == 0) {
                thread.setPriority(Thread.MAX_PRIORITY);
            } else thread.setPriority(Thread.MIN_PRIORITY);
            thread.setName("My Thread " + i);
        }
        String fileName = "log.txt";
        try (FileWriter writer = new FileWriter(fileName); PrintWriter pw = new PrintWriter(writer)) {
            for (int i = 0; i < count; i++) {
                Thread thread = threads[i];
                pw.println("CalculatorMain :Status of Thread " + i + " : " + thread.getState());
                states[i] = thread.getState();
            }

            for (int i = 0; i < count; i++) {
                threads[i].start();
            }

            boolean finish = false;
            while (!finish) {
                for (int i = 0; i < count; i++) {
                    Thread thread = threads[i];
                    if (thread.getState() != states[i]) {
                        writeThreadInfo(pw, thread, states[i]);
                    }
                }

                finish = true;
                for (int i = 0; i < count; i++) {
                    finish = finish && (threads[i].getState() == Thread.State.TERMINATED);
                }
            }

        }

    }

    private static void writeThreadInfo(PrintWriter pw, Thread thread, Thread.State state) {
        pw.printf("CalculatorMain : Id %d - %s \n", thread.getId(), thread.getName());
        pw.printf("CalculatorMain : Priority %d \n ", thread.getPriority());
        pw.printf("CalculatorMain : Old State  %s \n", state);
        pw.printf("CalculatorMain : new State %s \n", thread.getState());
        pw.printf("CalculatorMain : *******************\n");
    }
}
