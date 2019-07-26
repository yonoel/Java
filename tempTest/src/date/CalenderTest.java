package date;

import java.util.Calendar;
import java.util.Date;

public class CalenderTest {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018,0,1);
        calendar.roll(Calendar.YEAR,-1);
        System.out.println(calendar.get(Calendar.YEAR));
        try {
//            Date date = Calendar.getInstance().getTime();
//
//            System.out.println(date.getMonth());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
