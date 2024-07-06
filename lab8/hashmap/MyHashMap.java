package hashmap;

import java.util.*;

/**
 * A hash table-backed Map implementation. Provides amortized constant time
 * access to elements via get(), remove(), and put() in the best case.
 * <p>
 * Assumes null keys will never be inserted, and does not resize down upon remove().
 *
 * @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    @Override
    public void clear() {
        for (Collection<Node> bucket : buckets) {
            if (bucket != null) bucket.clear();
        }
        buckets = createTable(buckets.length);
        N = 0;
        M = 0;
        keyset.clear();
    }

    @Override
    public boolean containsKey(K key) {

        return keyset.contains(key);
    }

    @Override
    public V get(K key) {
        int index = Math.abs(key.hashCode() % buckets.length);
        if (buckets[index] != null) {
            for (Node x : buckets[index]) {
                if (x.key.equals(key)) return x.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return N;
    }

    private double getLoadFactor() {
        return (double) N / M;
    }

    private void putHelper(K key, V value, Collection<Node>[] buckets) {
        int index = Math.abs(key.hashCode() % buckets.length);
        if (buckets[index] == null) {
            buckets[index] = createBucket();
            M += 1;
        }
        boolean flag = true;
        for(Node node: buckets[index]){
            if(node.key == key) {
                node.value = value;
                flag = false;
            }
        }
        if(flag){
            buckets[index].add(createNode(key, value));
            N += 1;
        }

    }

    private void resize() {
        MyHashMap<K, V> newMap = new MyHashMap<>(buckets.length * 2, maxLoadFactor);
        for (K k : keySet()) {
            newMap.put(k, get(k));
        }
        Collection<Node>[] newBuckets = createTable(buckets.length * 2);
        M = N = 0;
        for (K key : keyset) {
            putHelper(key, get(key), newBuckets);
        }
        buckets = newBuckets;
    }

    @Override
    public void put(K key, V value) {
        // check maxLoadFactor
        //...//
        if (getLoadFactor() < maxLoadFactor) {
            resize();
        }

        putHelper(key, value, buckets);

        keyset.add(key);
    }

    @Override
    public Set<K> keySet() {
        return keyset;
    }

    @Override
    public V remove(K key) {
        V v = get(key);
        if(v==null)return null;
        int index = Math.abs(key.hashCode() % buckets.length);
        buckets[index].removeIf(node -> node.key == key);
        keyset.remove(key);
        return v;
    }


    @Override
    public V remove(K key, V value) {
        V v = get(key);
        if(v==value)return null;
        int index = Math.abs(key.hashCode() % buckets.length);
        buckets[index].removeIf(node -> node.key == key && node.value ==value);
        keyset.remove(key);
        return v;
    }

    @Override
    public Iterator<K> iterator() {
        return keyset.iterator();
    }

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
    Set<K> keyset = new HashSet<>();
    private double maxLoadFactor = 0.75;
    private int M;
    private int N;
    // You should probably define some more!

    /**
     * Constructors
     */
    public MyHashMap() {
        buckets = createTable(16);
    }

    public MyHashMap(int initialSize) {
        buckets = createTable(initialSize);

    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad     maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        buckets = createTable(initialSize);

        maxLoadFactor = maxLoad;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     * <p>
     * The only requirements of a hash table bucket are that we can:
     * 1. Insert items (`add` method)
     * 2. Remove items (`remove` method)
     * 3. Iterate through items (`iterator` method)
     * <p>
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     * <p>
     * Override this method to use different data structures as
     * the underlying bucket type
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new ArrayList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */

    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

}
