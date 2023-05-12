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

        for (int i = 0; i < 1000; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.25) {
                sad1.addLast(i);
                ads1.addLast(i);
            } else if (numberBetweenZeroAndOne < 0.5) {
                sad1.addFirst(i);
                ads1.addFirst(i);
            } else if (numberBetweenZeroAndOne < 0.75) {
                if (sad1.size() > 0) {
                    a = sad1.removeLast();
                    b = ads1.removeLast();
                    assertEquals("The last call to the removeFirst method, " +
                            "which should be return " + b + " but return " + a, b, a);
                }
            } else {
                if ( sad1.size() > 0){
                    a = sad1.removeFirst();
                    b = ads1.removeFirst();
                    assertEquals("The last call to the removeLast method, " +
                            "which should be return " + b + " but return " + a, b, a);
                }
            }
        }
    }

}
