package bstmap;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private BSTNode root;
    private int size;

    public BSTMap() {
        clear();
    }

    public void printInOrder() {
        System.out.println(toOrderedList());
    }

    public List<K> toOrderedList() {
        List<K> list = new ArrayList<>();
        toOrdered(root, list);
        return list;
    }

    private void toOrdered(BSTNode node, List<K> list) {
        if (node == null) return;
        // L D R
        if (node.left != null) {
            toOrdered(node.left, list);
        }
        list.add(node.key);
        if (node.right != null) {
            toOrdered(node.right, list);
        }
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        if (getNode(key) != null) {
            return true;
        }
        return false;
    }

    @Override
    public V get(K key) {
        BSTNode target = getNode(key);
        if (target != null) {
            return target.value;
        }
        return null;
    }

    public BSTNode getNode(K key) {
        BSTNode cur = root;
        while (cur != null) {
            int judge = key.compareTo(cur.key);
            if (judge > 0) {
                cur = cur.right;
            } else if (judge < 0) {
                cur = cur.left;
            } else {
                return cur;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        // add to right when the same <key,value> Node exits
        if (root == null) {
            root = new BSTNode(key, value);
            size += 1;
            return;
        }
        BSTNode target = root;
        while (target!=null) {
            int judge = key.compareTo(target.key);
            if (judge > 0) {
                if(target.right == null){
                    target.right = new BSTNode(key,value);

                    break;
                }
                target = target.right;
            } else if (judge < 0) {
                if(target.left == null){
                    target.left = new BSTNode(key,value);
                    break;
                }
                target = target.left;
            }
        }
        size += 1;
    }

    @Override
    public Set<K> keySet() {
        return Set.copyOf(toOrderedList());
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
        return keySet().iterator();
    }

    private class BSTNode {
        private K key;
        private V value;
        private BSTNode left;
        private BSTNode right;

        public BSTNode(K k, V v) {
            key = k;
            value = v;
            left = null;
            right = null;
        }
    }
}
