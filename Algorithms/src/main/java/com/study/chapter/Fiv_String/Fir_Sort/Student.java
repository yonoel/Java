package com.study.chapter.Fiv_String.Fir_Sort;

public class Student {
    private int group;
    private String name;

    public int key() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public Student(int group, String name) {
        this.group = group;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "group=" + group +
                ", name='" + name + '\'' +
                '}';
    }
}
