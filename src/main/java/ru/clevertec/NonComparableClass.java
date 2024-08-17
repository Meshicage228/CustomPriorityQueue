package ru.clevertec;

public class NonComparableClass {
    private Integer grade;

    public NonComparableClass(Integer grade) {
        this.grade = grade;
    }

    public Integer getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "ru.clevertec.NonComparableClass{" +
                "grade=" + grade +
                "}\n";
    }
}
