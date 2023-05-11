package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class MaxArrayDequeTest {

    public static class Comparator1 implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }
    }

    public static class Comparator2 implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }
    @Test
    public void test1(){
        MaxArrayDeque<Integer> deque1 = new MaxArrayDeque<Integer>(new Comparator1());
        for(int i = 0; i < 100; i++) {
            deque1.addLast(i);
        }
        assertEquals(99, (int)deque1.max());
    }

    @Test
    public void test2(){
        MaxArrayDeque<String> deque2 = new MaxArrayDeque<>(new Comparator2());
        deque2.addLast("hi");
        deque2.addLast("hello");
        deque2.addLast("ji");
        deque2.addLast("be");
        deque2.addLast("you");
        deque2.addLast("zi");
        deque2.addLast("my");
        deque2.addLast("name");
        deque2.addLast("is");
        deque2.addLast("tjp");
        deque2.printDeque();
        assertEquals("zi", deque2.max());

    }



}
