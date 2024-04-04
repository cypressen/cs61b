package deque;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void eightItem() {
        ArrayDeque<Integer> arr = new ArrayDeque<>();
        for (int i = 0; i < 8; i += 1) {
         arr.addFirst(i);
        }
        arr.printDeque();
        System.out.println(arr.size());
    }
}
