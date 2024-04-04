package deque;

public class ArrayDeque<T> implements Deque<T> {

    private T[] arrs;
    private int size;
    private int headIndex;
    private int tailIndex;

    public ArrayDeque() {
        arrs = (T[]) new Object[8];
        size = 0;
        headIndex = 1;
        tailIndex = 0;
    }

    private double getRate() {
        return (double) size / (double) arrs.length;
    }

    private int ezLoop(int index) {
        if (index == -1) {
            return arrs.length - 1;
        } else if (index == arrs.length) {
            return 0;
        }
        return index % arrs.length;
    }

    @Override
    public void addFirst(T item) {
        if (!isEmpty() && ezLoop(headIndex - 1) == tailIndex) {
            // resize //
        }
        headIndex = ezLoop(headIndex - 1);
        arrs[headIndex] = item;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (!isEmpty() && ezLoop(tailIndex + 1) == headIndex) {
            // resize //
        }
        tailIndex = ezLoop(tailIndex + 1);
        arrs[tailIndex] = item;
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        T getRemove = arrs[headIndex];
        arrs[headIndex] = null;
        headIndex = ezLoop(headIndex + 1);
        size -= 1;
        return getRemove;
    }

    @Override
    public T removeLast() {
        T getRemove = arrs[tailIndex];
        arrs[tailIndex] = null;
        tailIndex = ezLoop(tailIndex - 1);
        size -= 1;
        return getRemove;
    }

    @Override
    public T get(int index) {
        int targetIndex = ezLoop(headIndex + index);
        return arrs[targetIndex];
    }
}
