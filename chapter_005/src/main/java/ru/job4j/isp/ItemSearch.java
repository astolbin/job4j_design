package ru.job4j.isp;

public class ItemSearch implements Search {
    @Override
    public Item get(Item root, String number) {
        if (root.getNumber().equals(number)) {
            return root;
        }

        for (Item child : root.getChildren()) {
            Item item = get(child, number);
            if (item != null) {
                return item;
            }
        }

        return null;
    }
}
