package flik;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FlikTest {
    @Test
    public void bitsInTheTwoNumbers() {
        int i = 0;
        for (int j = 0; j < 500; j += 1, i += 1) {
            assertTrue(i ==j);
        }
    }
}
