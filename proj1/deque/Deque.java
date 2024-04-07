package deque;

import java.util.Iterator;

public interface Deque<T> {
    void addFirst(T item);

    void addLast(T item);

    int size();

    T removeFirst();

    T removeLast();

    T get(int index);


    boolean equals(Object o);

    default boolean isEmpty() {
        return size() == 0;
    }

     void printDeque();
}
