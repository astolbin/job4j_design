package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class LinkedList<E> implements Iterable<E> {

    private int size;
    private int modCount;
    private Node<E> first;
    private Node<E> last;

    public void add(E value) {
        Node<E> lastNode = last;
        Node<E> newNode = new Node<>(value, null);
        last = newNode;
        if (lastNode == null) {
            first = newNode;
        } else {
            lastNode.next = newNode;
        }
        size++;
        modCount++;
    }

    public E get(int index) {
        Objects.checkIndex(index, size);
        return getNode(index).value;
    }

    private Node<E> getNode(int index) {
        Node<E> rsl;
        rsl = first;

        for (int i = 0; i < index; i++) {
            rsl = rsl.next;
        }

        return rsl;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    private class Itr implements Iterator<E> {
        private int cursor = 0;
        private Node<E> currentNode = first;
        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            E rsl = currentNode.value;
            currentNode = currentNode.next;
            cursor++;
            return rsl;
        }
    }
}
