package iotest;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class testFile {
    private final static String IMPORT_MOUDLE_PATH = ".//导入模板/";
    private final static String DOWNLOAD_DIRS = "." + File.separator + "download" + File.separator;
    private final static int maxDepth = 5;

    public static void main(String[] args) {
        final String name = "test.txt";
//        String childPath = "/" + 123456412 + "/";
//        File file = new File(IMPORT_MOUDLE_PATH + childPath);
//        if (!file.exists()) file.mkdirs();// 创建该文件夹目录
//        System.out.println(file.exists());
        try {
            Path path = Paths.get(DOWNLOAD_DIRS);
            if (!Files.exists(path)) {
                System.out.println("!exsit");
                Files.createDirectories(path);
            }
            Stream<Path> files = Files.find(Paths.get(DOWNLOAD_DIRS), maxDepth, ((p, basicFileAttributes) -> name.equals(p.getFileName())
            ));

        } catch (Exception e) {

        }
    }
}
