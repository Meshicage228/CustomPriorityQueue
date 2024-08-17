package ru.clevertec;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class CustomPriorityQueue<T> {
    private final Comparator<? super T> comparator;

    private static final int DEFAULT_CAPACITY = 2;

    private T[] customQueue;

    private int size = 0;

    public CustomPriorityQueue(Class<T> tClass) {
        this(tClass, null);
    }

    public CustomPriorityQueue(Class<T> classType, Comparator<? super T> comparator) {
        this.comparator = comparator;

        if (comparator == null && !Comparable.class.isAssignableFrom(classType)) {
            throw new ClassCastException("No comparator or comparable provided");
        }

        customQueue = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public void add(T obj) {
        if (size == customQueue.length) {
            enlargeCapacity();
        }

        customQueue[size++] = obj;
        siftUp(obj);

    }

    public T poll() {
        if (size == 0) {
            return null;
        }

        T top = customQueue[0];
        customQueue[0] = customQueue[--size];
        customQueue[size] = null;
        siftDown();
        return top;
    }

    public T peek() {
        return customQueue[0];
    }

    public int getSize(){
        return size;
    }

    public int getLength(){
        return customQueue.length;
    }

    private void siftDown() {
        int start = 0;
        while (true) {
            int leftIndex = 2 * start + 1;
            int rightIndex = 2 * start + 2;
            int smaller = start;

            if (leftIndex < size && compare(customQueue[leftIndex], customQueue[smaller]) < 0) {
                smaller = leftIndex;
            } else if (rightIndex < size && compare(customQueue[rightIndex], customQueue[smaller]) <= 0) {
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
            T parent = customQueue[parentIndex - 1];
            if (compare(up, parent) >= 0) {
                break;
            }
            customQueue[currentPosition - 1] = parent;
            currentPosition = parentIndex;
            parentIndex = currentPosition / 2;
        }
        customQueue[currentPosition - 1] = up;
    }

    private int compare(T a, T b) {
            return Optional.ofNullable(comparator)
                    .map(c -> c.compare(a, b))
                    .orElseGet(() -> ((Comparable<T>) a).compareTo(b));
    }

    private void swap(int index1, int index2){
        T temp = customQueue[index1];
        customQueue[index1] = customQueue[index2];
        customQueue[index2] = temp;
    }

    private void enlargeCapacity() {
        customQueue = Arrays.copyOf(customQueue, customQueue.length * 2);
    }

}
