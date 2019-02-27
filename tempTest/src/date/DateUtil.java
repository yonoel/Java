package date;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    private static String format = "yyyy-MM-dd HH:mm:ss";

    private static String format1 = "yyyy-MM-dd";
    private static String format2 = "yyyy年MM月dd日";

    public static String convert(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
        return sdf.format(date);
    }

    public static String convert1(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format1, Locale.CHINA);
        return sdf.format(date);
    }

    public static String convert2(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format2, Locale.CHINA);
        return sdf.format(date);
    }

    public static String now(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());

    }

    public static Date format(String date) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = null;
        try {
            date1 = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date1;
    }
    public static Date format1(String date) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        try {
            date1 = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date1;
    }
    public static  String timestampToString(Timestamp timestamp) {
        SimpleDateFormat df = new SimpleDateFormat(format1);//定义格式，不显示毫秒
        return df.format(timestamp);
    }

    public static Timestamp getTime() {
        String date = convert(new Date());
        return Timestamp.valueOf(date);
    }
    public static Timestamp getTime1(String date) {
        date = date + " 00:00:00";
        return Timestamp.valueOf(date);
    }

    public static Boolean compareOneMinutes(Timestamp updatetime) {
        //判断验证码时间
        Calendar calendar = Calendar.getInstance();
        //把当前时间减去五分钟，如果为负数则会把年份往前推，比如2010年1月-3会变成2009年10月
        calendar.add(Calendar.MINUTE, -1);
        Timestamp time = new Timestamp(calendar.getTimeInMillis());
        //Timestamp date = entity.getUpdatetime();
        //判断是否过期，true就是过期
        if (time.before(updatetime)) {
            return false;
        }
        return true;
    }

    public static Boolean compareFiveMinutes(Timestamp updatetime) {
        //判断验证码时间
        Calendar calendar = Calendar.getInstance();
        //把当前时间减去五分钟，如果为负数则会把年份往前推，比如2010年1月-3会变成2009年10月
        calendar.add(Calendar.MINUTE, -5);
        Timestamp time = new Timestamp(calendar.getTimeInMillis());
        //判断是否过期，true就是过期
        if (time.after(updatetime)) {
            return false;
        }
        return true;
    }

    public static Boolean compareOneDay(Timestamp updatetime) {
        //判断验证码时间
        Calendar calendar = Calendar.getInstance();
        //把当前时间减去五分钟，如果为负数则会把年份往前推，比如2010年1月-3会变成2009年10月
        calendar.add(Calendar.DATE, -1);
        Timestamp time = new Timestamp(calendar.getTimeInMillis());
        //判断是否过期，true就是过期
        if (time.after(updatetime)) {
            return false;
        }
        return true;
    }

}
