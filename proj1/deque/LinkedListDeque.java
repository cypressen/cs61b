package deque;

// 1. ListNode引用的更好方式 -> 无 选择内部类
// 2. sentinel到底如何使用来化解特殊情况

import javax.management.ListenerNotFoundException;
import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {
    // 内部类
    private static class Node<T> {
        private T item;
        private Node<T> preNode;
        private Node<T> nextNode;

        public Node(T item) {
            this.item = item;
            preNode = null;
            nextNode = null;
        }

        public Node() {
        }
    }

    private int size;
    private final Node<T> sentinel;

    public LinkedListDeque() {
        sentinel = new Node<>();
        sentinel.nextNode = sentinel;
        sentinel.preNode = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        Node<T> newNode = new Node<>(item);
        sentinel.nextNode.preNode = newNode;
        newNode.nextNode = sentinel.nextNode;
        sentinel.nextNode = newNode;
        newNode.preNode = sentinel;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        Node<T> newNode = new Node<>(item);
        sentinel.preNode.nextNode = newNode;
        newNode.preNode = sentinel.preNode;
        sentinel.preNode = newNode;
        newNode.nextNode = sentinel;
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
        T it = sentinel.nextNode.item;
        sentinel.nextNode = sentinel.nextNode.nextNode;
        sentinel.nextNode.preNode = sentinel;
        size -= 1;
        return it;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T it = sentinel.preNode.item;
        sentinel.preNode = sentinel.preNode.preNode;
        sentinel.preNode.nextNode = sentinel;
        size -= 1;
        return it;
    }

    @Override
    public T get(int index) {
        if (index >= size || isEmpty()) {
            return null;
        }
        Node<T> itNode = sentinel.nextNode;
        for (int i = 0; i < index; i += 1) {
            itNode = itNode.nextNode;
        }
        return itNode.item;
    }

    private T getRecursiveHelper(int index, Node<T> curNode) {
        if (index == 0) {
            return curNode.item;
        }
        return getRecursiveHelper(index - 1, curNode.nextNode);
    }

    public T getRecursive(int index) {
        if (index >= size || isEmpty()) {
            return null;
        }
        return getRecursiveHelper(index, sentinel.nextNode);
    }
    @Override
    public void printDeque() {
        Node<T> it = sentinel.nextNode;
        while (it != sentinel) {
            System.out.print(it.item + " ");
            it = it.nextNode;
        }
        System.out.println();
    }
}
