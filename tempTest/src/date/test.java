package date;

import java.util.regex.Pattern;

public class test{
    public static void main(String[] args) {
//        String Date = "2018-12-10 00:00:00";
        int i = -1;
        System.out.println(i & 0x7fffffff);
        // ... -2^31的hash值
        // 对于最大的整数 Math.abs返回负值
        System.out.println("polygenelubricants".hashCode());
        System.out.println(System.currentTimeMillis());
        System.out.println(isNegativeInteger("-4520"));
    }
    private static String integerPattern = "^[-\\+]?[\\d]*$";
    private static String negativePattern = "^[-][\\d]*$";
    public static boolean isInteger(String string) {
        return getPatternInstance(integerPattern).matcher(string).matches();
    }
    public static boolean isNegativeInteger(String string){
        return getPatternInstance(negativePattern).matcher(string).matches();
    }
    private static Pattern getPatternInstance(String pattern){
        return Pattern.compile(pattern);
    }
}
