package deque;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void basicTest() {
        ArrayDeque<Integer> myArr = new ArrayDeque<>();

        myArr.addLast(9);
        myArr.addFirst(2);
        myArr.addFirst(3);
        assertEquals(3, myArr.size());
        assertEquals(3, (int) myArr.get(0));
        assertEquals(2, (int) myArr.get(1));
        assertEquals(9, (int) myArr.get(2));
    }
}
