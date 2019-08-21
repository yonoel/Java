package com.study.springdata.Util;

import com.csvreader.CsvReader;
import com.study.springdata.springdatastudy.pojo.User;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CSVUtil {
    static List<String> names = new ArrayList<>();
    static Predicate<User> userPredicate = user -> !names.contains(user.getName());
    static List<User> users;

    public static void main(String[] args) throws IOException {
        users = getUsers();
        String text;
        int count;


        text = "带🌟的抽取三名";
        Predicate<User> starPredicate = user1 -> user1.getScore() >= 3000;
        count = 3;
        doSelect(text, starPredicate, count);


        Predicate<User> ninePredicate = user -> user.getScore() >= 2700 && user.getScore() < 3000;
        text = "九段的抽取两名";
        count = 2;
        doSelect(text, ninePredicate, count);

        Predicate<User> eightPredicate = user -> user.getScore() >= 2400 && user.getScore() < 2700;
        text = "八段的抽取一名";
        count = 1;
        doSelect(text, eightPredicate, count);

        Predicate<User> scorePredicate = user -> user.getScore() >= 1800 && user.getScore() < 2400;
        text = "1800-2400的抽取2名";
        count = 2;
        doSelect(text, scorePredicate, count);


        text = "周功勋前十取一的是：";
        List<User> oneList = users.stream().filter(userPredicate)
                .sorted(Comparator.comparingInt(User::getScore)).limit(10).collect(Collectors.toList());
        List<String> strings = getNames(oneList, 1);
        names.addAll(strings);
        printlnTemplate(text, strings);


        Predicate<User> feat300Predicate = user -> user.getWeekFeat() > 300;
        text = "周功勋300以上随机抽取2名：";
        count = 2;
        doSelect(text, feat300Predicate, count);
    }

    private static void doSelect(String text, Predicate<User> ninePredicate, int count) {
        List<User> starList = users.stream().filter(userPredicate).filter(ninePredicate).collect(Collectors.toList());
        List<String> name = getNames(starList, count);
        names.addAll(name);
        printlnTemplate(text, name);
    }

    private static List<User> getUsers() throws IOException {
        String filePath = "/Users/yonoel/Downloads/menu2.csv";
        CsvReader reader = new CsvReader(filePath, ',', Charset.forName("UTF-8"));
        reader.readHeaders();

        // 1:name 8:本周功勋 9:累计功勋 10：斗技
        List<User> users = new LinkedList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        while (reader.readRecord()) {
            try {
                String joinTime = reader.get(5);
                String loginTime = reader.get(6);
                User.UserBuilder builder = User.builder()
                        .id(reader.get(0))
                        .name(reader.get(1))
                        .nickName(reader.get(2))
                        .level(Integer.parseInt(reader.get(3)))
                        .weekFeat(Integer.parseInt(reader.get(8)))
                        .totalFeat(Integer.parseInt(reader.get(9)))
                        .score(Integer.parseInt(reader.get(10)));
                if (!StringUtils.isEmpty(loginTime)) {
                    builder.loginTime(dateFormat.parse(loginTime));
                }
                if (!StringUtils.isEmpty(joinTime)) {
                    builder.joinTime(dateFormat.parse(joinTime));
                }
                users.add(builder.build());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        reader.close();
        return users;
    }


    private static void printlnTemplate(String text, List<String> name) {
        System.out.println(text);
        name.forEach(System.out::println);
        System.out.println();
    }

    private static List<String> getNames(List<User> otherGroup, int count) {
        List<String> names = new ArrayList<>(count);
        while (count != 0) {
            Random random = new Random();
            int index = random.nextInt(otherGroup.size());
            String name = otherGroup.get(index).getName();
            names.add(name);
            otherGroup.remove(index);
            count--;
        }
        return names;
    }


}
