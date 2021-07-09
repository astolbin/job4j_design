package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    List<Store> stores = new ArrayList<>();

    public void rout(Food food) {
        for (Store store : stores) {
            if (store.add(food)) {
                return;
            }
        }

        throw new IllegalStateException("Rout fail");
    }

    public void addStore(Store store) {
        stores.add(store);
    }
}
