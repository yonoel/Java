package date;

import java.util.Calendar;
import java.util.Date;

public class CalenderTest {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = Calendar.getInstance().getTime();
            System.out.println(date.getMonth());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
