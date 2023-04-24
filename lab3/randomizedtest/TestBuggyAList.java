package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> list1 = new AListNoResizing<>();
        BuggyAList<Integer>list2 = new BuggyAList<>();
        for(int i = 0; i <3; i++) {
            list1.addLast(i+4);
            list2.addLast(i+4);
        }
        for (int i = 0; i <3; i++) {
           assertEquals(list1.removeLast(),list2.removeLast());
        }


    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer>L2 = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                L2.addLast(randVal);
                //System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                //System.out.println("size: " + size);
                if(size>0){
                    int a = L.getLast();
                    int b = L2.getLast();
                    assertEquals(a, b);
                    //System.out.println("get( " + a + " " + b + ")");

                }

            }else{
                if(L.size()>0){
                   int a = L.removeLast();
                   int b = L2.removeLast();
                   assertEquals(a, b);
                   // System.out.println("remove( " + a + " " + b + ")");
                }
            }
        }
    }
}
