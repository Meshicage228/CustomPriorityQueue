import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.clevertec.ComparableClass;
import ru.clevertec.CustomPriorityQueue;
import ru.clevertec.NonComparableClass;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CustomPriorityClass tests")
class CustomPriorityQueueTest {

    private CustomPriorityQueue<NonComparableClass> usingComparator;
    private CustomPriorityQueue<ComparableClass> comparableClasses;
    private CustomPriorityQueue<NonComparableClass> nonComparableClassesReverse;

    @BeforeEach
    public void setUp(){
        usingComparator  = new CustomPriorityQueue<>(NonComparableClass.class, Comparator.comparing(NonComparableClass::getGrade));
        comparableClasses = new CustomPriorityQueue<>(ComparableClass.class);
        nonComparableClassesReverse = new CustomPriorityQueue<>(NonComparableClass.class, (o1, o2) -> Integer.compare(o2.getGrade(), o1.getGrade()));
    }

    @Test
    @DisplayName("Add value using comparator test")
    public void addValue(){
        usingComparator.add(new NonComparableClass(2));
        usingComparator.add(new NonComparableClass(4));
        usingComparator.add(new NonComparableClass(3));
        usingComparator.add(new NonComparableClass(0));
        usingComparator.add(new NonComparableClass(1));

        assertEquals(0, usingComparator.peek().getGrade());
        assertEquals(5, usingComparator.getSize());

        usingComparator.add(new NonComparableClass(-100));

        assertEquals(-100, usingComparator.peek().getGrade());
    }

    @Test
    @DisplayName("Add value using reverse comparator test")
    public void addReverseValue(){
        nonComparableClassesReverse.add(new NonComparableClass(2));
        nonComparableClassesReverse.add(new NonComparableClass(4));
        nonComparableClassesReverse.add(new NonComparableClass(3));
        nonComparableClassesReverse.add(new NonComparableClass(0));
        nonComparableClassesReverse.add(new NonComparableClass(1));

        assertEquals(4, nonComparableClassesReverse.peek().getGrade());
        assertEquals(5, nonComparableClassesReverse.getSize());

        nonComparableClassesReverse.add(new NonComparableClass(100));

        assertEquals(100, nonComparableClassesReverse.peek().getGrade());
    }

    @Test
    @DisplayName("Poll value comparable test")
    public void pollValue(){
        comparableClasses.add(new ComparableClass(11, "Vlad"));
        comparableClasses.add(new ComparableClass(33, "Nick"));
        comparableClasses.add(new ComparableClass(30, "Olga"));
        comparableClasses.add(new ComparableClass(9, "Temmy"));
        comparableClasses.add(new ComparableClass(4, "Gorge"));

        assertEquals(5, comparableClasses.getSize());
        assertEquals(4, comparableClasses.poll().getAge());
        assertEquals(4, comparableClasses.getSize());
    }

    @Test
    @DisplayName("No comparator or comparable provided test")
    public void getException(){
        assertThrows(ClassCastException.class, () -> {
            new CustomPriorityQueue<>(NonComparableClass.class);
        });
    }

    @Test
    @DisplayName("Enlarged capacity test")
    public void enlargeCapacity(){
        for (int i = 0; i < 10; i++) {
            usingComparator.add(new NonComparableClass(32));
        }

        assertEquals(16, usingComparator.getLength());
    }

    @Test
    @DisplayName("Check queue values are sorted")
    public void sortedCheck(){
        usingComparator.add(new NonComparableClass(2));
        usingComparator.add(new NonComparableClass(4));
        usingComparator.add(new NonComparableClass(3));
        usingComparator.add(new NonComparableClass(0));
        usingComparator.add(new NonComparableClass(1));

        int prevValue = Integer.MIN_VALUE;
        for (int i = 0; i < usingComparator.getSize(); i++) {
            int currValue = usingComparator.poll().getGrade();
            assertTrue(prevValue <= currValue);
            prevValue = currValue;
        }
    }
}