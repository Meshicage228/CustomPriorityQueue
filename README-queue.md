# Custom realisation of PriorityQueue

This is my realisation of PriorityQueue for Clevertec course. This Repository contains source code of mine implementation.


## About Project

* A class with `poll()` `peek()` `add()` `getSize()` `getLength()` methods.
* Works with Comparator and Comparable interfaces.
* Uses `Generics` so can hold any Type.
* Stores elements at the `array`.
* `Binary Heap logic` : min element at the top by default.
* Own implementation of `siftUp` and `siftDown` procedures.
* `Initial capacity is 8` : enlarges automatically.
* Contains base `Unit tests` to check functionality.

## Getting Started with Initialization

1. This code will create CustomPriorityQueue with `reversed Comparator`.

```
CustomPriorityQueue<Integer> q = new CustomPriorityQueue<>(Integer.class, Comparator.reverseOrder());
```

2. This code will create CustomPriorityQueue with Class which implemented `Comparable Interface`.

```
public class YourClassNameHere implements Comparable<YourClassNameHere>{

    @Override
    public int compareTo(YourClassNameHere o) {
        // Your comparison
    }
}
```

```
CustomPriorityQueue<YourClassNameHere> q = new CustomPriorityQueue<>(YourClassNameHere.class);
```

Queue will compare your type with the logic, which you specified with Comparable <u> instead of using Comparator. </u>

<a id ="exception-id">3.</a> Following code will throw `ClassCastException`, because you should always specify Comparator or Comparable with your custom types.

```
CustomPriorityQueue<NonComarableClass> q = new CustomPriorityQueue<>(NonComarableClass.class);
```

### CustomPriorityQueue usage

Following code proves that methods working properly :
```
CustomPriorityQueue<Integer> q = new CustomPriorityQueue<>(Integer.class);

  q.add(10);
  q.add(-10);

  System.out.println("Current size " + q.getSize()); // 2
  System.out.println("Current head value with poll : " + q.poll()); // -10
  System.out.println("Size of queue after poll method : " + q.getSize()); // 1
  System.out.println("Current head value with peek : " + q.peek()); // 10
```
### Unit Tests

Project contains base unit test to check code works properly.
Contains tests that check methods : `add()` `poll()` `peek()` `getSize()` `getLength()` `ClassCastException `

### Attention here!

Be careful: when initializing the queue you may get a `ClassCastException`: [described here](#exception-id)

## Thanks for your visiting!