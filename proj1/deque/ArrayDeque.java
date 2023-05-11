package deque;

import java.util.Iterator;


public class ArrayDeque<T> implements Iterable<T> {



    private class ArrayDequeIterator implements Iterator<T> {

        int position;
        public ArrayDequeIterator(){
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


    private T[] items;
    //跟踪下一个addFirst操作插入元素的位置
    private int nextFirst;
    //跟踪下一个addLast操作插入元素的位置
    private int nextLast;
    private int size;

    public ArrayDeque() {
        items = (T[])new Object[8];
        nextFirst = 5;
        nextLast = 6;
        size = 0;
    }

    /*重新调整数组的大小*/
    public void resize(int capbility){
        T [] a = (T[])new Object[capbility];
        for(int i = 0; i<size; i++){
            a[i] = get(i);
        }
        items = a;
        nextFirst = capbility -1;
        nextLast = size;
    }

    /*Adds an item of type T to the front of the deque. You can assume that item is never null.*/
    public void addFirst(T item) {
        if (size == items.length){
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst -= 1;
        if (nextFirst == -1){
            nextFirst = items.length -1;   ////nextFirst超过数组的左边界需要回到末尾（循环）
        }
        size += 1;
    }

    /*Adds an item of type T to the back of the deque. You can assume that item is never null.*/
    public void addLast(T item) {
        if (size == items.length){
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast += 1;
        if(nextLast == items.length){
            nextLast = 0;   //nextLast超过数组的右边界需要回到开头（循环）
        }

        size += 1;
    }

    /*Returns true if deque is empty, false otherwise.*/
    public boolean isEmpty() {
        return size == 0;
    }

    /*Returns the number of items in the deque.*/
    public int size() {
        return size;
    }


    /*Prints the items in the deque from first to last, separated by a space.
     Once all the items have been printed, print out a new line.*/
    public void printDeque() {

        for(int i = 0; i < size; i++) {

            System.out.println(get(i).toString() + " ");
        }
        System.out.println();
    }


    /*Removes and returns the item at the front of the deque. If no such item exists, returns null.*/
    public T removeFirst() {
        if (size == 0) {return null;}
        if (((double)size / items.length) < 0.25){   //两个整数相除得到的结果仍然是整数，如果您想要得到浮点数结果，需要将其中一个整数强制转换为浮点数类型
            resize((int)(items.length/4));
        }

        T returnItem = get(0);
        if (nextFirst== items.length -1){
            nextFirst = -1;
        }
        items[nextFirst + 1] = null;
        //items[(nextFirst + 1) % items.length] = null;
        nextFirst += 1;
        size -= 1;
        return returnItem;
    }

    /*Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast() {
        if (size == 0) {return null;}
        if (((double)size / items.length) < 0.25){  //两个整数相除得到的结果仍然是整数，如果您想要得到浮点数结果，需要将其中一个整数强制转换为浮点数类型
            resize((int)(items.length/4));
        }
        T returnItem = get(size -1);
        if (nextLast == 0){
            nextLast = items.length; //进行移除的时候，后指针到达左边界的时候，再进行移除时后指针要
        }
        items[nextLast-1] = null;
        //items[(nextLast - 1) % items.length] = null;  不行java中-1%8的值为-1，而python中为7
        nextLast -= 1;
        size -= 1;
        return returnItem;
    }


    /*Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    If no such item exists, returns null. Must not alter the deque!*/
    /* must use iteration */
    public T get(int index) {
        if(index >= size || index<0) {return null;}
        return items[(nextFirst + 1 + index) % items.length]; //索引超过数组的右边界需要回到开头（循环）
    }


    @Override
    public Iterator<T> iterator() {
        return new ArrayDeque.ArrayDequeIterator();
    }

    /*Returns whether the parameter o is equal to the Deque.
    o is considered equal if it is a Deque and if it contains the same contents
    (as governed by the generic T’s equals method) in the same order
    */
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null){
            return false;
        }
        if (!(o instanceof ArrayDeque<?>)){
            return false;
        }
        ArrayDeque<T> temp = (ArrayDeque<T>)o;
        if(temp.size() != this.size()){
            return false;
        }
        for(int i=0; i < temp.size(); i++){
            if (! this.get(i).equals(temp.get(i))){
                return false;
            }
        }
        return true;
    }



    public static void main(String[] args){
        ArrayDeque<Integer> deque1 = new ArrayDeque<>();
        deque1.addFirst(18);
        deque1.addFirst(19);
        deque1.addFirst(20);
        deque1.addFirst(17);
        deque1.addFirst(16);
        deque1.addFirst(15);
        deque1.addFirst(14);
        System.out.println(deque1.removeFirst());
        System.out.println(deque1.removeFirst());

        System.out.println(deque1.removeLast());
        deque1.addLast(15);

        System.out.println(deque1.get(2));
        System.out.println(deque1.size());
        deque1.printDeque();

       /* for(int item : deque1){
            System.out.println(item);
        }
        LinkedListDeque<String> deque2 = new LinkedListDeque<>();
        deque2.addFirst("Hi");
        deque2.addLast("Java");
        deque2.printDeque();

        LinkedListDeque<String> deque3 = new LinkedListDeque<>();
        deque3.addFirst("Hi");
        deque3.addLast("Java");

        LinkedListDeque<String> deque4 = new LinkedListDeque<>();
        deque4.addFirst("hi");
        deque4.addLast("Java");

        LinkedListDeque<String> deque5 = new LinkedListDeque<>();
        deque5.addFirst("Hi");
        deque5.addLast("Java");
        deque5.addLast("!");


        System.out.println(deque2.equals(deque1));
        System.out.println(deque2.equals(deque2));
        System.out.println(deque1.equals(deque1));
        System.out.println(deque2.equals(null));
        System.out.println(deque2.equals(deque3));
        System.out.println(deque2.equals(deque4));
        System.out.println(deque2.equals(deque5));
*/
    }
}
