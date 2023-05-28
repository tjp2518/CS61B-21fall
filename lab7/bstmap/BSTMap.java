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

    private BSTNode node;

    public BSTMap(){

    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        if (node != null) {
            node = null;
        }
    }
    /** Returns true if this map contains a mapping for the specified key. */

    @Override
    public boolean containsKey(K key) {

        return containsKey(node, key);
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
        return get(node, key);
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
        return size(node);
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

        node = put(node, key, value);
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
        return remove(this.node, key);

    }

    private V remove(BSTNode bstNode, K key){
        if (bstNode == null) {
            return null;
        }
        int cmp1 = key.compareTo(bstNode.key);
        V returnValue;
        if (cmp1 > 0) {
            returnValue = remove(bstNode.right, key);
        } else if (cmp1 < 0) {
            returnValue = remove(bstNode.left, key);
        }else {
            returnValue = bstNode.value;
            BSTNode parentNode = findParent(this.node, bstNode.key);
            if (bstNode.left == null && bstNode.right == null) {

                if (bstNode.key.compareTo(parentNode.key) > 0) {
                    parentNode.right = null;
                } else {
                    parentNode.left = null;
                }
                return returnValue;
            }else if(bstNode.left != null && bstNode.right == null) {
                if (bstNode.key.compareTo(parentNode.key) > 0) {
                    parentNode.right = bstNode.left;
                } else {
                    parentNode.left = bstNode.left;
                }
            } else if (bstNode.left == null && bstNode.right != null){

                if (bstNode.key.compareTo(parentNode.key) > 0) {
                    parentNode.right = bstNode.right;
                } else {
                    parentNode.left = bstNode.right;
                }
            }else{
                //BSTNode PrecursorNode = findPrecursor(root);
                BSTNode SuccessorNode = findSuccessor(bstNode);
                remove(bstNode, SuccessorNode.key, SuccessorNode.value);
                bstNode.key = SuccessorNode.key;
                bstNode.value = SuccessorNode.value;
            }



        }
        return returnValue;
    }

    @Override
    public V remove(K key, V value) {
        return remove(node, key, value);
    }

    private V remove(BSTNode bstNode, K key, V value) {
        if (bstNode == null) {
            return null;
        }
        int cmp1 = key.compareTo(bstNode.key);
        V returnValue;
        if (cmp1 > 0) {
            returnValue = remove(bstNode.right, key, value);
        } else if (cmp1 < 0) {
            returnValue = remove(bstNode.left, key, value);
        }else {
            if (!bstNode.value.equals(value)) {
                return null;
            }
            returnValue = bstNode.value;
            BSTNode parentNode = findParent(this.node, bstNode.key);
            if (bstNode.left == null && bstNode.right == null) {

                if (bstNode.key.compareTo(parentNode.key) > 0) {
                    parentNode.right = null;
                } else {
                    parentNode.left = null;
                }
                return returnValue;
            }else if(bstNode.left != null && bstNode.right == null) {
                if (bstNode.key.compareTo(parentNode.key) > 0) {
                    parentNode.right = bstNode.left;
                } else {
                    parentNode.left = bstNode.left;
                }
            } else if (bstNode.left == null && bstNode.right != null){

                if (bstNode.key.compareTo(parentNode.key) > 0) {
                    parentNode.right = bstNode.right;
                } else {
                    parentNode.left = bstNode.right;
                }
            }else{
                //BSTNode PrecursorNode = findPrecursor(root);
                BSTNode SuccessorNode = findSuccessor(bstNode);
                remove(bstNode, SuccessorNode.key, SuccessorNode.value);
                bstNode.key = SuccessorNode.key;
                bstNode.value = SuccessorNode.value;
            }



        }
        return returnValue;
    }
    // 找到node中具有key的节点的父节点
    public BSTNode findParent(BSTNode bstNode, K key) {
        if (bstNode.left == null && bstNode.right == null){
            return null;
        }else if (bstNode.left != null && bstNode.right == null) {
            if (bstNode.left.key.equals(key)) {
                return bstNode;
            }else {
                return findParent(bstNode.left, key);
            }
        }else if (bstNode.left == null && bstNode.right != null){
            if (bstNode.right.key.equals(key)) {
                return bstNode;
            }else {
                return findParent(bstNode.right, key);
            }
        }else{
            if (bstNode.left.key.equals(key) || bstNode.right.key.equals(key)) {
                return bstNode;
            }else {
                BSTNode returnValue1 = findParent(bstNode.left, key);
                BSTNode returnValue2 = findParent(bstNode.right, key);
                if (returnValue1 != null) {
                    return returnValue1;
                }else {
                    return returnValue2;
                }
            }
        }

    }

    //找到root的前驱节点
    private BSTNode findPrecursor(BSTNode bstNode){
        return null;
    }

    //找到root的后继节点  调用这个函数root的都是具有两个子节点的，root.right一定不为空。
    private BSTNode findSuccessor(BSTNode bstNode) {
            return lastmostNode(bstNode.right);
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();

    }

    /**prints out your BSTMap in order of increasing Key*/
    public void printInOrder() {
        if (node == null) {
            return;
        }
        printInOrder(node);
    }
    /**辅助函数，naked recursive的， 按键值增加的顺序打印 root*/
    private void printInOrder(BSTNode bstNode) {
        if (bstNode == null) {
            return;
        }
        printInOrder(bstNode.left);
        System.out.println("key: " + bstNode.key.toString() + "    " + "value: " + bstNode.value.toString());
        printInOrder(bstNode.right);
    }


    /**辅助函数，找到root的最左边的节点, 如果root为null 返回null*/
    private BSTNode lastmostNode(BSTNode bstNode) {
        if (bstNode.left == null) {
            return bstNode;
        }else {

            return lastmostNode(bstNode.left);
        }
    }

}
