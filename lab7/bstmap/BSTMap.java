package bstmap;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {


    private class BSTNode {
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

    public BSTMap() {

    }

    /**
     * Removes all of the mappings from this map.
     */
    @Override
    public void clear() {
        if (node != null) {
            node = null;
        }
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     */

    @Override
    public boolean containsKey(K key) {

        return containsKey(node, key);
    }

    /**
     * 辅助函数 ，naked recursive 返回root中是否包含key
     */

    private boolean containsKey(BSTNode root, K key) {

        if (root == null) {
            return false;
        }
        int cmp = key.compareTo(root.key);
        boolean returnValue; //跟踪是否找到具有相同key的BST节点
        if (cmp == 0) {
            returnValue = true;
        } else if (cmp > 0) {
            returnValue = containsKey(root.right, key);
        } else {
            returnValue = containsKey(root.left, key);
        }
        return returnValue;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return get(node, key);
    }

    /**
     * 辅助函数 naked recursive的 如果root包含key， 返回root中该key对应的value
     */
    private V get(BSTNode root, K key) {
        if (root == null) {
            return null;
        }
        int cmp = key.compareTo(root.key);
        V returnValue;
        if (cmp > 0) {
            returnValue = get(root.right, key);
        } else if (cmp < 0) {
            returnValue = get(root.left, key);
        } else {
            returnValue = root.value;
        }
        return returnValue;
    }

    @Override
    public int size() {
        return size(node);
    }

    /**
     * 辅助函数，方便返回null节点的size为0，
     */
    private int size(BSTNode root) {
        if (root == null) {
            return 0;
        }
        return root.size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     */
    @Override
    public void put(K key, V value) {

        node = put(node, key, value);
    }

    /**
     * 辅助函数，这是 naked recursive 将键值对加入root中  注意二叉树的添加新节点一定在叶子节点处添加
     */
    private BSTNode put(BSTNode root, K key, V value) {

        if (root == null) {
            root = new BSTNode(key, value, 1);
            return root;
        }
        int cmp = key.compareTo((K) root.key);
        if (cmp > 0) {
            root.right = put(root.right, key, value);
        } else if (cmp < 0) {
            root.left = put(root.left, key, value);
        } else {
            root.value = value;
        }
        root.size = 1 + size(root.left) + size(root.right);
        return root;
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        Set<K> bstmapkeys = new TreeSet<>();
        return keySet(node, bstmapkeys);
    }

    private Set<K> keySet(BSTNode bstNode, Set<K> bstmapkeys) {
        if (bstNode == null) {
            return null;
        } else {
            keySet(bstNode.left, bstmapkeys);
            bstmapkeys.add(bstNode.key);
            keySet(bstNode.right, bstmapkeys);
        }
        return bstmapkeys;
    }

    @Override
    public V remove(K key) {

        V returnItem = get(key);//node中包含key则返回相应的value，不包含返回的是null
        node = remove(node, key);
        return returnItem;

    }

    // ----------------------------------------------------------------remove的简单实现，
    // 参考https://algs4.cs.princeton.edu/32bst/BST.java.html

    //返回移除key之后的bstNode
/*    private BSTNode remove(BSTNode bstNode, K key) {
        if (bstNode == null) {
            return null;
        }
        int cmp = key.compareTo(bstNode.key);
        if (cmp < 0) {
            bstNode.left = remove(bstNode.left, key);
        } else if (cmp > 0){
            bstNode.right = remove(bstNode.right, key);
        } else {

            //此时表示bstNode就是我们要移除的节点
            //bstNode有一个子节点或者无子节点的情况
           if (bstNode.left == null) {
               return bstNode.right;
           }
           if (bstNode.right == null) {
               return bstNode.left;
           }

           //bstNode有两个子节点的情况
            BSTNode tempNode = bstNode;

           //下面是以后继节点为根节点创建新的树。这样方便修改节点的size

            bstNode = findSuccessor(bstNode);//找到bstNode的右子树的最小节点，作为新树的root
            //注意这里必须先移除右子树的最小节点，然后在给左子树赋值，两个操作的顺序不能颠倒！！！！
            bstNode.right = removeMin(tempNode.right); //去掉原右子树的最小节点的右子树作为新树的右子树
            bstNode.left = tempNode.left;  //原树的左子树作为新树的左子树

        }
        //在外层
        bstNode.size = 1 + size(bstNode.left) + size(bstNode.right); //更新新树的size
        return bstNode;
    }*/

    private BSTNode remove(BSTNode bstNode, K key) {
        if (bstNode == null) {
            return null;
        }
        int cmp = key.compareTo(bstNode.key);
        if (cmp < 0) {
            bstNode.left = remove(bstNode.left, key);
        } else if (cmp > 0){
            bstNode.right = remove(bstNode.right, key);
        } else {

            //此时表示bstNode就是我们要移除的节点
            //bstNode有一个子节点或者无子节点的情况
           if (bstNode.left == null) {
               return bstNode.right;
           }
           if (bstNode.right == null) {
               return bstNode.left;
           }

           //bstNode有两个子节点的情况
            BSTNode suNode ;

            suNode = findSuccessor(bstNode);//找到bstNode的右子树的最小节点，作为新树的root
            bstNode.right = removeMin(bstNode.right);
            bstNode.key = suNode.key;
            bstNode.value = suNode.value;
        }
        //在外层
        bstNode.size = 1 + size(bstNode.left) + size(bstNode.right); //更新新树的size
        return bstNode;
    }

    //去除bstNode中最小的节点
    private BSTNode removeMin(BSTNode bstNode) {
        if (bstNode.left == null) {
            return bstNode.right;
        } else {
            bstNode.left = removeMin(bstNode.left);
            bstNode.size = 1 + size(bstNode.left) + size(bstNode.right);
            return bstNode;
        }

    }










    /*private V remove(BSTNode bstNode, K key) {
        if (bstNode == null) {
            return null;
        }
        int cmp1 = key.compareTo(bstNode.key);
        V returnValue;
        if (cmp1 > 0) {
            returnValue = remove(bstNode.right, key);
        } else if (cmp1 < 0) {
            returnValue = remove(bstNode.left, key);
        } else {
            returnValue = bstNode.value;
            BSTNode parentNode = findParent(this.node, key);
            if (bstNode.left == null && bstNode.right == null) {
                if (parentNode == null) {
                    this.node = null;
                } else if (bstNode.key.compareTo(parentNode.key) > 0) {
                    changeSize(bstNode);
                    parentNode.right = null;

                } else {
                    changeSize(bstNode);
                    parentNode.left = null;
                }
            } else if (bstNode.left != null && bstNode.right == null) {
                if (parentNode == null) {
                    this.node = node.left;
                } else if (bstNode.key.compareTo(parentNode.key) > 0) {
                    changeSize(bstNode);
                    parentNode.right = bstNode.left;
                } else {
                    changeSize(bstNode);
                    parentNode.left = bstNode.left;
                }
            } else if (bstNode.left == null && bstNode.right != null) {
                if (parentNode == null) {
                    this.node = node.right;
                } else if (bstNode.key.compareTo(parentNode.key) > 0) {
                    changeSize(bstNode);
                    parentNode.right = bstNode.right;
                } else {
                    changeSize(bstNode);
                    parentNode.left = bstNode.right;
                }
            } else {
                BSTNode successorNode = findSuccessor(bstNode);
                remove(bstNode, successorNode.key);
                bstNode.key = successorNode.key;
                bstNode.value = successorNode.value;
            }


        }
        return returnValue;
    }
*/
    /**
     * 改变从根节点到参数节点bstNode的父节点的路径上的所有节点的size
     */
    private void changeSize(BSTNode bstNode) {
        BSTNode parentNode = findParent(node, bstNode.key);
        if (parentNode == null) {
            return;
        } else {
            parentNode.size -= 1;
            changeSize(parentNode);
        }
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
        } else {
            if (!bstNode.value.equals(value)) {
                return null;
            }
            returnValue = bstNode.value;
            BSTNode parentNode = findParent(this.node, key);
            if (bstNode.left == null && bstNode.right == null) {
                if (parentNode == null) {
                    this.node = null;
                } else if (bstNode.key.compareTo(parentNode.key) > 0) {
                    changeSize(bstNode);
                    parentNode.right = null;

                } else {
                    changeSize(bstNode);
                    parentNode.left = null;
                }
            } else if (bstNode.left != null && bstNode.right == null) {
                if (parentNode == null) {
                    this.node = node.left;
                } else if (bstNode.key.compareTo(parentNode.key) > 0) {
                    changeSize(bstNode);
                    parentNode.right = bstNode.left;
                } else {
                    changeSize(bstNode);
                    parentNode.left = bstNode.left;
                }
            } else if (bstNode.left == null && bstNode.right != null) {
                if (parentNode == null) {
                    this.node = node.right;
                } else if (bstNode.key.compareTo(parentNode.key) > 0) {
                    changeSize(bstNode);
                    parentNode.right = bstNode.right;
                } else {
                    changeSize(bstNode);
                    parentNode.left = bstNode.right;
                }
            } else {
                BSTNode successorNode = findSuccessor(bstNode);
                remove(bstNode, successorNode.key, value);
                bstNode.key = successorNode.key;
                bstNode.value = successorNode.value;
            }


        }
        return returnValue;
    }

    // 找到node中具有key的节点的父节点
    private BSTNode findParent(BSTNode bstNode, K key) {
        if (bstNode.left == null && bstNode.right == null) {
            return null;
        } else if (bstNode.left != null && bstNode.right == null) {
            if (bstNode.left.key.equals(key)) {
                return bstNode;
            } else {
                return findParent(bstNode.left, key);
            }
        } else if (bstNode.left == null && bstNode.right != null) {
            if (bstNode.right.key.equals(key)) {
                return bstNode;
            } else {
                return findParent(bstNode.right, key);
            }
        } else {
            if (bstNode.left.key.equals(key) || bstNode.right.key.equals(key)) {
                return bstNode;
            } else {
                BSTNode returnValue1 = findParent(bstNode.left, key);
                BSTNode returnValue2 = findParent(bstNode.right, key);
                if (returnValue1 != null) {
                    return returnValue1;
                } else {
                    return returnValue2;
                }
            }
        }

    }

    //找到root的前驱节点
    private BSTNode findPrecursor(BSTNode bstNode) {
        if (bstNode.left == null) {
            return null;
        } else {
            return rightmost(bstNode.left);
        }
    }

    /**
     * 找到以参数bstNode为根节点的节点的最右边的节点
     */
    private BSTNode rightmost(BSTNode bstNode) {
        if (bstNode.right == null) {
            return bstNode;
        } else {
            return rightmost(bstNode.right);
        }
    }


    //找到root的后继节点  调用这个函数root的都是具有两个子节点的，root.right一定不为空。
    /*    private BSTNode findSuccessor(BSTNode bstNode) {
                return lastmostNode(bstNode.right);
        }*/

    /**
     * 返回以bstNode为root的节点的后继节点，如果该节点无子节点，返回null即可
     */
    private BSTNode findSuccessor(BSTNode bstNode) {
        if (bstNode.right == null) {
            return null;
        } else {
            return leftmostNode(bstNode.right);
        }
    }


    @Override
    public Iterator<K> iterator() {
        Set<K> keysSet = keySet();
        return keysSet.iterator();
    }


    /**
     * prints out your BSTMap in order of increasing Key
     */
    public void printInOrder() {
        if (node == null) {
            return;
        }
        printInOrder(node);
    }

    /**
     * 辅助函数，naked recursive的， 按键值增加的顺序打印 root
     */
    private void printInOrder(BSTNode bstNode) {
        if (bstNode == null) {
            return;
        }
        printInOrder(bstNode.left);
        System.out.println("key: " + bstNode.key.toString() + "    " + "value: " + bstNode.value.toString());
        printInOrder(bstNode.right);
    }


    /**
     * 辅助函数，找到root的最左边的节点, 如果root为null 返回null
     */
    private BSTNode leftmostNode(BSTNode bstNode) {
        if (bstNode.left == null) {
            return bstNode;
        } else {

            return leftmostNode(bstNode.left);
        }
    }

}
