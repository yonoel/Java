package MultiThread;

import com.sun.tools.corba.se.idl.ExceptionEntry;

public class InterruptedTest {
    public static void main(String[] args) {
        try {
            System.out.println(" thread start");
            while (true) {
                Thread.sleep(500);
                System.out.println(" thread running");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
