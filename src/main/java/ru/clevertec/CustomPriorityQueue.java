package ru.clevertec;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class CustomPriorityQueue<T> {
    private final Comparator<? super T> comparator;

    private static final int DEFAULT_CAPACITY = 8;

    private T[] queue;

    private int size = 0;

    public CustomPriorityQueue(Class<T> tClass) {
        this(tClass, null);
    }

    public CustomPriorityQueue() {
        comparator = (Comparator<T>) Comparator.naturalOrder();
    }

    public CustomPriorityQueue(Class<T> classType, Comparator<? super T> comparator) {
        this.comparator = comparator;

        if (comparator == null && !Comparable.class.isAssignableFrom(classType)) {
            throw new ClassCastException("No comparator or comparable provided");
        }

        queue = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public void add(T obj) {
        if (size == queue.length) {
            enlargeCapacity();
        }

        if (obj == null) {
            return;
        }

        queue[size++] = obj;
        siftUp(obj);
    }

    public T poll() {
        if (size == 0) {
            return null;
        }

        T top = queue[0];
        size -= 1;
        queue[0] = queue[size];
        queue[size] = null;
        siftDown();
        return top;
    }

    public T peek() {
        return queue[0];
    }

    public int getSize() {
        return size;
    }

    public int getLength() {
        return queue.length;
    }

    private void siftDown() {
        int start = 0;
        while (true) {
            int leftIndex = 2 * start + 1;
            int rightIndex = 2 * start + 2;
            int smaller = start;

            if (leftIndex < size && compare(queue[leftIndex], queue[smaller]) < 0) {
                smaller = leftIndex;
            } else if (rightIndex < size && compare(queue[rightIndex], queue[smaller]) <= 0) {
                smaller = rightIndex;
            } else {
                break;
            }

            swap(start, smaller);

            start = smaller;
        }
    }

    private void siftUp(T up) {
        int currentPosition = size;
        int parentIndex = currentPosition / 2;

        while (parentIndex > 0) {
            T parent = queue[parentIndex - 1];
            if (compare(up, parent) >= 0) {
                break;
            }
            queue[currentPosition - 1] = parent;
            currentPosition = parentIndex;
            parentIndex = currentPosition / 2;
        }
        queue[currentPosition - 1] = up;
    }

    private int compare(T a, T b) {
        return Optional.ofNullable(comparator)
                .map(c -> c.compare(a, b))
                .orElseGet(() -> ((Comparable<T>) a).compareTo(b));
    }

    private void swap(int index1, int index2) {
        T temp = queue[index1];
        queue[index1] = queue[index2];
        queue[index2] = temp;
    }

    private void enlargeCapacity() {
        queue = Arrays.copyOf(queue, queue.length * 2);
    }

}
