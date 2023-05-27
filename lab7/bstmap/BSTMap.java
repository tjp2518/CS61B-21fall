package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {


    private  class BSTNode {
        private K key;
        private V value;
        private BSTNode left;
        private BSTNode right;
        int size;  //用来跟踪以此节点为root的树有多少个节点。
        public BSTNode() {

        }
        public BSTNode(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    private BSTNode root;

    public BSTMap(){

    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        if (root != null) {
            root = null;
        }
    }
    /** Returns true if this map contains a mapping for the specified key. */

    @Override
    public boolean containsKey(K key) {

        return containsKey(root, key);
    }

    /** 辅助函数 ，naked recursive 返回root中是否包含key */

    private boolean containsKey(BSTNode root, K key) {

        if (root == null) {
            return false;
        }
        int cmp = key.compareTo(root.key);
        boolean returnValue; //跟踪是否找到具有相同key的BST节点
        if (cmp == 0) {
            returnValue = true;
        }else if (cmp > 0) {
            returnValue = containsKey(root.right, key);
        }else {
            returnValue = containsKey(root.left, key);
        }
        return returnValue;
    }

    /** Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return get(root, key);
    }

    /** 辅助函数 naked recursive的 如果root包含key， 返回root中该key对应的value */
    private V get(BSTNode root, K key) {
        if (root == null) {
            return null;
        }
        int cmp = key.compareTo(root.key);
        V returnValue;
        if (cmp > 0) {
            returnValue = get(root.right, key);
        }else if(cmp < 0) {
            returnValue = get(root.left, key);
        }else {
            returnValue = root.value;
        }
        return returnValue;
    }
    @Override
    public int size() {
        return size(root);
    }

    /** 辅助函数，方便返回null节点的size为0，*/
    private int size(BSTNode root) {
        if(root == null) {
            return 0;
        }
        return root.size;
    }

    /** Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {

        root = put(root, key, value);
    }

    /**辅助函数，这是 naked recursive 将键值对加入root中  注意二叉树的添加新节点一定在叶子节点处添加*/
    private BSTNode put(BSTNode root, K key, V value){

        if (root == null){
            root = new BSTNode(key, value, 1);
            return root;
        }
        int cmp = key.compareTo((K)root.key);
        if (cmp > 0){
            root.right = put(root.right, key, value);
        }else if (cmp < 0) {
            root.left = put(root.left, key, value);
        }else {
            root.value = value;
        }
        root.size = 1 + size(root.left) + size(root.right);
        return root;
    }


    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();

    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();

    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();

    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();

    }

    /**prints out your BSTMap in order of increasing Key*/
    public void printInOrder() {
        if (root == null) {
            return;
        }
        printInOrder(root);
    }
    /**辅助函数，naked recursive的， 按键值增加的顺序打印 root*/
    private void printInOrder(BSTNode root) {
        printInOrder(root.left);
        System.out.println("key: " + root.key.toString() + "value: " + root.value.toString());
        printInOrder(root.right);
    }


    /**辅助函数，找到root的最左边的节点, 如果root为null 返回null*/
    private BSTNode lastmostNode(BSTNode root) {
        if (root.left == null) {
            return root;
        }else {

            return lastmostNode(root.left);
        }
    }

}
