package ru.clevertec;

public class ComparableClass implements Comparable<ComparableClass>{
    private int age;
    private String name;

    @Override
    public int compareTo(ComparableClass o) {
        return this.age - o.age;
    }

    public ComparableClass(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }


    @Override
    public String toString() {
        return "ru.clevertec.ComparableClass{" +
                "age=" + age +
                ", name='" + name + '\'' +
                "}\n";
    }
}
