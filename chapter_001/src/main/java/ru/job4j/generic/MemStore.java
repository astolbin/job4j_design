package ru.job4j.generic;

import java.util.List;
import java.util.ArrayList;

public class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean rsl = false;
        int index = getIndex(id);

        if (checkIndex(index)) {
            mem.set(index, model);
            rsl = true;
        }

        return rsl;
    }

    @Override
    public boolean delete(String id) {
        boolean rsl = false;
        int index = getIndex(id);

        if (checkIndex(index)) {
            mem.remove(index);
            rsl = true;
        }

        return rsl;
    }

    @Override
    public T findById(String id) {
        T rsl = null;
        int index = getIndex(id);

        if (checkIndex(index)) {
            rsl = mem.get(index);
        }

        return rsl;
    }

    private int getIndex(String id) {
        int rsl = -1;

        for (int i = 0; i < mem.size(); i++) {
            if (mem.get(i).getId().equals(id)) {
                rsl = i;
                break;
            }
        }

        return rsl;
    }

    private boolean checkIndex(int index) {
        return index >= 0;
    }
}
