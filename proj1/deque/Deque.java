package deque;

import java.util.Iterator;

public interface Deque<T> {
    public void addFirst(T item);

    public void addLast(T item);

    public int size();

    public T removeFirst();

    public T removeLast();

    public T get(int index);


    public boolean equals(Object o);

    default public boolean isEmpty() {
        return size() == 0;
    }

    default public void printDeque() {
        for (int i = 0; i < size(); i += 1) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }
}
