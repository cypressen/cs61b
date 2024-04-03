package deque;
import org.junit.Test;
import static org.junit.Assert.*;
public class ADTest {

    @Test
    public void basicTest(){
        ArrayDeque<Integer> arr1 = new ArrayDeque<>();
        ArrayDeque<Integer> arr2 = new ArrayDeque<>();
        arr1.addFirst(2);
        arr1.addLast(10);
        System.out.println(arr1.size());
        arr1.removeFirst();
        assertEquals(10,(int)arr1.get(0));
        System.out.println(arr1.size());

    }
}
