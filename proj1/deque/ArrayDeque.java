package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

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

    private int ezLoop(int index) {
        if (index == -1) {
            return arrs.length - 1;
        } else if (index == arrs.length) {
            return 0;
        }
        return index % arrs.length;
    }

    private void resize(int cap) {
        T[] newArrs = (T[]) new Object[cap];
        for (int i = 0; i < size; i += 1) {
            newArrs[i] = get(i);
        }
        headIndex = 0;
        tailIndex = size - 1;
        arrs = newArrs;
    }

    @Override
    public void addFirst(T item) {
        if (!isEmpty() && ezLoop(headIndex - 1) == tailIndex) {
            resize(arrs.length * 2);
        }
        headIndex = ezLoop(headIndex - 1);
        arrs[headIndex] = item;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (!isEmpty() && ezLoop(tailIndex + 1) == headIndex) {
            resize(arrs.length * 2);
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
        if (isEmpty()) {
            return null;
        }
        T getRemove = arrs[headIndex];
        arrs[headIndex] = null;
        headIndex = ezLoop(headIndex + 1);
        size -= 1;

        if (size != 0 && size * 4 < arrs.length) {
            resize(arrs.length / 2);
        }

        return getRemove;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T getRemove = arrs[tailIndex];
        arrs[tailIndex] = null;
        tailIndex = ezLoop(tailIndex - 1);
        size -= 1;

        if (size != 0 && size * 4 < arrs.length) {
            resize(arrs.length / 2);
        }

        return getRemove;
    }

    @Override
    public T get(int index) {
        int targetIndex = ezLoop(headIndex + index);
        return arrs[targetIndex];
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Deque) || ((Deque<?>) o).size() != this.size()) {
            return false;
        }
        if (o == this) {
            return true;
        }
        for (int i = 0; i < this.size(); i++) {
            Object item = ((Deque<?>) o).get(i);
            if (!(this.get(i).equals(item))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size(); i += 1) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int wizPos;

        private ArrayDequeIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T item = get(wizPos);
            wizPos += 1;
            return item;
        }
    }
}
