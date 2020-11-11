package ru.job4j.collection;

import java.util.Optional;
import java.util.Queue;
import java.util.LinkedList;
import java.util.function.Predicate;

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

    public boolean isBinary() {
        return filter(el -> el.children.size() > 2).isEmpty();
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return filter(el -> el.value.equals(value));
    }

    private Optional<Node<E>> filter(Predicate<Node<E>> filter) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);

        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (filter.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }

        return rsl;
    }
}
