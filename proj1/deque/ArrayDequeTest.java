package deque;

import org.junit.Test;
import org.junit.Assert.*;

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


}


