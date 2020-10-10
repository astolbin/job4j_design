package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public T deleteFirst() {
        checkHead();

        T deleted = head.value;
        head = head.next;

        return deleted;
    }

    public T deleteLast() {
        checkHead();
        Node<T> tail = head;

        if (head.next == null) {
            head = null;
        } else {
            Node<T> prev = tail;

            while (tail.next != null) {
                prev = tail;
                tail = tail.next;
            }

            prev.next = null;
        }

        return tail.value;
    }

    public void revert() {
        checkHead();

        Node<T> nodeCurrent = head;
        Node<T> nodePrev = null;
        Node<T> nodeNext;

        while (nodeCurrent.next != null) {
            nodeNext = nodeCurrent.next;
            nodeCurrent.next = nodePrev;
            nodePrev = nodeCurrent;
            nodeCurrent = nodeNext;
        }

        nodeCurrent.next = nodePrev;
        head = nodeCurrent;
    }

    private void checkHead() {
        if (head == null) {
            throw new NoSuchElementException();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
