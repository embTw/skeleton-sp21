package randomizedtest;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        correct.addLast(4);
        broken.addLast(4);
        correct.addLast(5);
        broken.addLast(5);
        correct.addLast(6);
        broken.addLast(6);
        // first
        correct.removeLast();
        broken.removeLast();
        assertEquals(correct.size(), broken.size());
        for (int i = 0; i < correct.size(); i++) {
            assertEquals(correct.get(i), broken.get(i));
        }
        // second
        correct.removeLast();
        broken.removeLast();
        assertEquals(correct.size(), broken.size());
        for (int i = 0; i < correct.size(); i++) {
            assertEquals(correct.get(i), broken.get(i));
        }
        // third
        correct.removeLast();
        broken.removeLast();
        assertEquals(correct.size(), broken.size());
        for (int i = 0; i < correct.size(); i++) {
            assertEquals(correct.get(i), broken.get(i));
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> L1 = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            // getLast and removeLast are not allowed when L.size is 0
            if (L.size() == 0 && operationNumber > 1)
                continue;
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
                L1.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                System.out.println("size: " + size);
                size = L1.size();
                System.out.println("size: " + size);
                assertEquals(L.size(), L1.size());
            } else if (operationNumber == 2) {
                System.out.println("last element of L: " + L.getLast());
                System.out.println("last element of L1: " + L1.getLast());
                assertEquals(L.getLast(), L1.getLast());
            } else if (operationNumber == 3) {
                Integer removed = L.removeLast();
                Integer removed1 = L1.removeLast();
                System.out.println("L remove last element: " + removed);
                System.out.println("L1 remove last element: " + removed1);
                assertEquals(removed, removed1);
            }
        }
    }
}
