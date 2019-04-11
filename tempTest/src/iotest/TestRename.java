package iotest;

import java.io.File;
import java.io.IOException;

public class TestRename {
    public static void main(String[] args) {
        File file = new File("1.txt");
        try {
            System.out.println(file.createNewFile());
            System.out.println(file.renameTo(new File("2.txt")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
