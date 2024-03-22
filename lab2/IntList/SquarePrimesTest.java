package IntList;

import static org.junit.Assert.*;

import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimeReturnFalse() {
        IntList lst = IntList.of(14, 15, 16, 20, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 20 -> 18", lst.toString());
        assertFalse(changed);
    }

    @Test
    public void testSquarePrimeLast() {
        IntList lst = IntList.of(14, 15, 16, 20, 17);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 20 -> 289", lst.toString());
        assertTrue(changed);
    }
    @Test
    public void testSquarePrimeFirst() {
        IntList lst = IntList.of(17, 15, 16, 20, 14);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("289 -> 15 -> 16 -> 20 -> 14", lst.toString());
        assertTrue(changed);
    }
    @Test
    public void testSquarePrimeOnePrime() {
        IntList lst = IntList.of(17);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("289", lst.toString());
        assertTrue(changed);
    }
    @Test
    public void testSquarePrimeNullList() {
        IntList lst = IntList.of(1,2,3);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("1 -> 4 -> 9", lst.toString());
        assertTrue(changed);
    }
}
