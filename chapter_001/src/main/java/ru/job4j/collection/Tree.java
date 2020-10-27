package ru.job4j.collection;

import java.util.Optional;
import java.util.Queue;
import java.util.LinkedList;

public class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        var parentContainer = findBy(parent);

        if (parentContainer.isPresent() && findBy(child).isEmpty()) {
            var childrenList = parentContainer.get().children;
            childrenList.add(new Node<>(child));
            rsl = true;
        }

        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
