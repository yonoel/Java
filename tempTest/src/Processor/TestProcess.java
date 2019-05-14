package Processor;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TestProcess {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            try {
                Process process = Runtime.getRuntime().exec("python3 /Users/yonoel/Desktop/java/Java/tempTest/resources/demo.py");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String string;
                while ((string = bufferedReader.readLine()) != null)
                    System.out.println(string);
                process.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
    }

}
