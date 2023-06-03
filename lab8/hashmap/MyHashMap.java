package hashmap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int initialSize;
    private double loadFactor;
    private int size;
    /** Constructors */
    public MyHashMap() {
        this.initialSize = 16;
        this.loadFactor = 0.75;
        this.size = 0;
        this.buckets = createTable(this.initialSize);
    }

    public MyHashMap(int initialSize) {
        this.initialSize = initialSize;
        this.loadFactor = 0.75;
        this.size = 0;
        this.buckets = createTable(this.initialSize);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.initialSize = initialSize;
        this.loadFactor = maxLoad;
        this.size = 0;
        this.buckets = createTable(this.initialSize);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {

        return new ArrayList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] tempBuckets = new Collection[tableSize];
        for (int i = 0; i < tempBuckets.length; i++) {
            tempBuckets[i] = createBucket();
        }
        return tempBuckets;
    }


    // Your code won't compile until you do so!



    /**
     * Removes all of the mappings from this map.
     */
    @Override
    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = null;
        }
        this.size = 0;
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        int index = indexBucket(key);
        if (buckets[index] != null) {
            for (Node node : this.buckets[index]) {
                if (node.key.equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        int index = indexBucket(key);
        if (buckets[index] != null) {
            for (Node node : this.buckets[index]) {
                if (node.key.equals(key)) {
                    return node.value;
                }
            }
        }
        return null;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        Node node = createNode(key, value);
        int index = indexBucket(key);
        Collection<Node> bucket = buckets[index];
        for (Node item : bucket) {
            if (item.key.equals(key)) {
                item.value = value;
                return;
            }
        }
        bucket.add(node);

        this.size += 1;
        if ((double) this.size / buckets.length > loadFactor) {
            resize(buckets.length * 2);
        }

    }
    /**
     * 返回参数key 对应的bucket索引*/
    private int indexBucket(K key) {
        int hashCode = key.hashCode();
        return Math.floorMod(hashCode, buckets.length);
    }

    /**
     * 以capacity为buckets数量重调this.buckets的数量*/
    private void resize(int capacity) {
        MyHashMap<K, V> tempMap = new MyHashMap<>(capacity, this.loadFactor);
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                for (Node item : buckets[i]) {
                    tempMap.put(item.key, item.value);
                }
            }
        }
        this.buckets = tempMap.buckets;
    }

    /**
     * Returns a Set view of the keys contained in this map.
     */
    @Override
    public Set<K> keySet() {
        Set<K> keysMap = new HashSet<>();
        for (Collection<Node> bucket : buckets) {
            for (Node item : bucket) {
                keysMap.add(item.key);
            }
        }
        return keysMap;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        int index = indexBucket(key);
        V returnValue = get(key);
        if (returnValue == null) {
            return null;
        }
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                buckets[index].remove(node);
                this.size -= 1;
                return returnValue;
            }
        }
        return returnValue;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     *
     * @param key
     * @param value
     */
    @Override
    public V remove(K key, V value) {
        int index = indexBucket(key);
        V returnValue = get(key);
        if (returnValue == null || !returnValue.equals(value)) {
            return null;
        }
        for (Node node : buckets[index]) {
            if (node.key.equals(key) && node.value.equals(value)) {
                buckets[index].remove(node);
                this.size -= 1;
                return returnValue;

            }
        }
        return returnValue;

    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        Set<K> keysMap = keySet();
        return keysMap.iterator();
    }


}
