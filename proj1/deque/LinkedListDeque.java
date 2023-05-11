package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {

    /* Implementing recursive linked list nodes */
    private  class LLNode {
        private T item;
        private LLNode prev;
        private LLNode next;

        LLNode(LLNode node1, T i, LLNode node2) {
            item = i;
            prev = node1;
            next = node2;
        }
    }

    /* */
    private class LinkListDequeIterator implements Iterator<T> {

        int position;
        LinkListDequeIterator() {
            position = 0;
        }
        @Override
        public boolean hasNext() {
            return position < size;
        }

        @Override
        public T next() {
            T returnItem = get(position);
            position += 1;
            return returnItem;
        }
    }

    private LLNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new LLNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }


    @Override
    /*Adds an item of type T to the front of the deque. You can assume that item is never null.*/
    public void addFirst(T item) {
        LLNode tempNode = sentinel.next; // temp node to save previously created LLnode instance
        LLNode newNode = new LLNode(sentinel, item, tempNode);
        //The front pointer of the new node points to the sentinel node
        // and the back pointer points to the previously created LLnode
        tempNode.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }
    @Override
    /*Adds an item of type T to the back of the deque. You can assume that item is never null.*/
    public void addLast(T item) {
        LLNode tempNode = sentinel.prev; // temp node to save the last node
        LLNode newNode = new LLNode(tempNode, item, sentinel);
        tempNode.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }
    /*@Override
    *//*Returns true if deque is empty, false otherwise.*//*
    public boolean isEmpty() {
        return size == 0;
    }*/

    @Override
    /*Returns the number of items in the deque.*/
    public int size() {
        return size;
    }

    @Override
    /*Prints the items in the deque from first to last, separated by a space.
     Once all the items have been printed, print out a new line.*/
    public void printDeque() {
        int i = 0;
        LLNode temp = sentinel.next;
        while (i < size) {
            System.out.print(temp.item.toString() + " ");
            i += 1;
            temp = temp.next;
        }
        System.out.println();
    }

    @Override
    /*Removes and returns the item at the front of the deque.
    If no such item exists, returns null.*/
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        LLNode removeNode = this.sentinel.next;
        this.sentinel.next = this.sentinel.next.next;
        removeNode.next.prev = this.sentinel;
        removeNode.prev = null;
        removeNode.next = null;
        size -= 1;
        return removeNode.item;
    }

    @Override
    /*Removes and returns the item at the back of the deque.
    If no such item exists, returns null. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        LLNode removeNode = this.sentinel.prev;
        this.sentinel.prev = removeNode.prev;
        removeNode.prev.next = sentinel;
        removeNode.next = null;
        removeNode.prev = null;
        size -= 1;
        return removeNode.item;
    }



    @Override
    /*Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    If no such item exists, returns null. Must not alter the deque!*/
    /* must use iteration */
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        int i = 0;
        LLNode temp = this.sentinel.next;
        while (i != index) {
            temp = temp.next;
            i += 1;
        }
        return temp.item;
    }


    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return getRecursive(index, this.sentinel.next);
    }

    /*通过构造辅助函数实现递归的方法*/
    private T getRecursive(int index, LLNode temp) {
        if (index == 0) {
            return temp.item;
        } else {
            return getRecursive(index - 1, temp.next);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkListDequeIterator();
    }




    /*Returns whether the parameter o is equal to the Deque.
    o is considered equal if it is a Deque and if it contains the same contents
    (as governed by the generic T’s equals method) in the same order
    */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Deque<?>)) {
            return false;
        }
        LinkedListDeque<T> temp = (LinkedListDeque<T>) o;
        if (temp.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < temp.size(); i++) {
            if (!this.get(i).equals(temp.get(i))) {
                return false;
            }
        }
        return true;
    }

}
