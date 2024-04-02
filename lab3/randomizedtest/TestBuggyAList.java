package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE
//    My CODE
//    @Test
//    public void testThreeAddThreeRemove() {
//        BuggyAList<Integer> bugArr = new BuggyAList<>();
//        AListNoResizing<Integer> cmpArr = new AListNoResizing<>();
//        bugArr.addLast(4);
//        cmpArr.addLast(4);
//        bugArr.addLast(5);
//        cmpArr.addLast(5);
//        bugArr.addLast(6);
//        cmpArr.addLast(6);
//        for (int i = 0; i < 3; i += 1) {
//            assertEquals(cmpArr.get(i), bugArr.get(i));
//        }
//        for (int i = 0; i < 3; i += 1) {
//            bugArr.removeLast();
//            cmpArr.removeLast();
//            for (int j = 1; j < 3 - i; j += 1) {
//                assertEquals(cmpArr.get(j), bugArr.get(j));
//            }
//        }
//
//    }
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        correct.addLast(5);
        correct.addLast(10);
        correct.addLast(15);

        broken.addLast(5);
        broken.addLast(10);
        broken.addLast(15);

        assertEquals(correct.size(), broken.size());

        assertEquals(correct.removeLast(), broken.removeLast());
        assertEquals(correct.removeLast(), broken.removeLast());
        assertEquals(correct.removeLast(), broken.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> cmp = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                cmp.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int sizeL = L.size();
                int sizeCmp = cmp.size();
                assertEquals(sizeL,sizeCmp);
            } else if (operationNumber == 2) {
                // getLast
                if (L.size() != 0 && L.size() == cmp.size()) {
                    int valL = L.getLast();
                    int valCmp = cmp.getLast();
                    assertEquals(valL,valCmp);
                }
            } else if (operationNumber == 3) {
                // removeLast
                if (L.size() != 0 && L.size() == cmp.size()) {
                    int valL = L.removeLast();
                    int valCmp = cmp.removeLast();
                    assertEquals(valL,valCmp);
                }
            }
        }
    }


}
