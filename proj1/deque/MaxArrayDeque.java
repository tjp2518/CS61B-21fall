package deque;

import java.util.Comparator;


/**The MaxArrayDeque can either tell you the max element in itself by using the Comparator<T> given to it in the constructor,
 * or an arbitrary Comparator<T> that is different from the one given in the constructor.*/
public class MaxArrayDeque<T> extends ArrayDeque<T>{

    private Comparator<T> comparator;

    //private static class

    /**creates a MaxArrayDeque with the given Comparator.
     * */
    public MaxArrayDeque(Comparator<T> c){
        super();
        comparator = c;
    }


    /** returns the maximum element in the deque as governed by the previously given Comparator.
     * If the MaxArrayDeque is empty, simply return null.*/
    public T max(){
        if (this.size() == 0){
            return null;
        }
        T returnItem = this.get(0);
        for (T item : this){
            if (comparator.compare(item, returnItem) > 0) {
                returnItem = item;
            }
        }
        return  returnItem;
    }

    /**returns the maximum element in the deque as governed by the parameter Comparator c.
     * If the MaxArrayDeque is empty, simply return null.*/
    public T max(Comparator<T> c){
        if (this.size() == 0){
            return null;
        }
        T returnItem = this.get(0);
        for (T item : this){
            if (c.compare(item, returnItem) > 0) {
                returnItem = item;
            }
        }
        return  returnItem;
    }


}
