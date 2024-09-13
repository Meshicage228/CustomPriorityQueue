package ru.clevertec;

import java.util.Comparator;

public class CustomPriorityQueueMain {
    public static void main(String[] args) {
        CustomPriorityQueue<Integer> q = new CustomPriorityQueue<>(Integer.class, Comparator.reverseOrder());
        q.add(10);
        q.add(20);
        q.add(null);

        System.out.println(q.peek());
    }
}
