package tester;
import static org.junit.Assert.*;

import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;
public class TestArrayDequeEC {

    @Test
    public void test() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();
        Integer a;
        Integer b;
        StringBuilder returnMsg = new StringBuilder("\n");

        for (int i = 0; i < 1001; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.25) {
                sad1.addLast(i);
                ads1.addLast(i);
                returnMsg.append("addLast(").append(i).append(")\n");
            } else if (numberBetweenZeroAndOne < 0.5) {
                sad1.addFirst(i);
                ads1.addFirst(i);
                returnMsg.append("addFirst(").append(i).append(")\n");

            } else if (numberBetweenZeroAndOne < 0.75) {
                if (sad1.size() > 0) {
                    a = sad1.removeLast();
                    b = ads1.removeLast();
                    returnMsg.append("removeLast(").append(")\n");
                    assertEquals(returnMsg.toString(), b, a);
                }
            } else {
                if (sad1.size() > 0) {
                    a = sad1.removeFirst();
                    b = ads1.removeFirst();
                    returnMsg.append("removeFirst(").append(")\n");
                    assertEquals(returnMsg.toString(), b, a);
                }
            }
        }
    }

}
