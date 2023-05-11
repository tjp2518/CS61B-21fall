package deque;

import org.junit.Test;
import org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArrayDequeTest {

    /* 测试没有resize的addFirst和removeFirst方法*/
    @Test
    public void test1(){
        ArrayDeque<Integer> deque1 = new ArrayDeque<>();
        for (int i = 0; i < 8; i++){
            deque1.addFirst(i);
        }
        for (int i = 0; i < 8; i++){
            deque1.removeFirst();
        }
        assertTrue("deque1 should be empty after removal", deque1.isEmpty());

    }
    @Test
    public void test2(){
        ArrayDeque<Integer> deque2 = new ArrayDeque<>();
        for (int i = 0; i < 8; i++){
            deque2.addLast(i);
        }
        for (int i = 0; i < 8; i++){
            deque2.removeLast();
        }
        assertTrue("deque2 should be empty after removal", deque2.isEmpty());
    }

    @Test
    public void test3(){
        ArrayDeque<Integer> deque3 = new ArrayDeque<>();
        for (int i = 0; i < 8; i++){
            deque3.addLast(i);
        }
        for (int i = 0; i < 8; i++){
            deque3.removeFirst();
        }
        assertTrue("deque2 should be empty after removal", deque3.isEmpty());
    }

    @Test
    public void test4() {
        ArrayDeque<Integer> deque4 = new ArrayDeque<>();
        for (int i = 0; i < 8; i++) {
            deque4.addFirst(i);
        }
        for (int i = 0; i < 8; i++) {
            deque4.removeLast();
        }
        assertTrue("deque2 should be empty after removal", deque4.isEmpty());
    }
    @Test
    public void test6(){
        ArrayDeque<Integer> deque6 = new ArrayDeque<>();
        ArrayDeque<Integer> deque7 = new ArrayDeque<>();
        ArrayDeque<String> deque8 = new ArrayDeque<>();
        ArrayDeque<String> deque9 = new ArrayDeque<>();
        ArrayDeque<String> deque10 = new ArrayDeque<>();


        for (int i = 0; i < 24; i++) {
            deque6.addFirst(i);
        }
        for (int i = 23; i >= 0; i--) {
            deque7.addLast(i);
        }
        for (int i = 0; i < 24; i++) {
            deque8.addFirst("hi");
        }
        for (int i = 23; i >= 0; i--) {
            deque9.addLast("hi");
        }
        for (int i = 0; i < 24; i++) {
            deque10.addFirst("Hi");
        }
        assertTrue("deque6 should be equal deque7", deque6.equals(deque7));
        assertTrue("deque7 should be equal deque6", deque7.equals(deque6));
        assertTrue("deque7 should't be equal null", !deque7.equals(null));
        assertTrue("deque8 should't be equal deque7", !deque8.equals(deque7));
        assertTrue("deque8 should be equal deque9", deque8.equals(deque9));
        assertTrue("deque8 should be equal deque9", !deque8.equals(deque10));




    }

    @Test
    public void test5() {
        ArrayDeque<Integer> deque5 = new ArrayDeque<>();
        for (int i = 0; i < 16; i++){
            deque5.addFirst(i);
        }
        deque5.addLast(-1);
        deque5.addLast(-2);
        deque5.addLast(-3);
        deque5.addLast(-4);
        deque5.addLast(-5);
        deque5.addLast(-6);
        deque5.addLast(-7);
        deque5.addLast(-8);
        deque5.addLast(-9);
        deque5.addLast(-10);
        deque5.removeFirst();
        deque5.removeLast();
        deque5.removeFirst();
        deque5.removeFirst();
        deque5.removeLast();
        deque5.removeLast();

        deque5.removeLast();
        for (int i = 0; i < 16; i++){
            deque5.removeFirst();
        }

    }

    /*test iterator*/
    @Test
    public void test7(){
        ArrayDeque<Integer> deque11 = new ArrayDeque<>();
        for (int i = 0; i <24; i++){
            deque11.addLast(i);
        }
        for (int item : deque11){
            System.out.print(item + " ");
        }
        System.out.println();
    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());

    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }

    }
    @Test
    public void testEqual(){
        ArrayDeque<Integer> deque1 = new ArrayDeque<>();
        LinkedListDeque<Integer> deque2 = new LinkedListDeque<>();
        for (int i = 0; i < 18; i++){
            deque1.addLast(i);
            deque2.addLast(i);
        }
        assertTrue("deque1 should be deque2", deque1.equals(deque2));
    }

}


