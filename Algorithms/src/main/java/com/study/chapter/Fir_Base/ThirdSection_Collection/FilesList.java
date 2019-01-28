package com.study.chapter.Fir_Base.ThirdSection_Collection;

import java.io.File;

public class FilesList {
    // 1.3.43 打印出该文件夹下的所有文件并用递归的方式（缩进）列出其下的所有文件
    public static void main(String[] args) {
        String name = args[0];
        File file = new File(name);
    }

    public static void readFile(File file, int indent) {
        File[] list = file.listFiles();
        for (File newFile : list) {
            for (int i = 0; i < indent; i++) {
                System.out.printf("\t");
            }
            System.out.println(newFile.getName());
            if(newFile.isDirectory()){
                readFile(newFile,indent+1);
            }
        }
        return;
    }
}
