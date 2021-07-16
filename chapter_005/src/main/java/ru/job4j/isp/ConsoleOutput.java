package ru.job4j.isp;

public class ConsoleOutput implements Output {

    @Override
    public void show(Item root) {
        String prefix = "";
        for (Item child : root.getChildren()) {
            System.out.printf(
                    "%s %s %s %s",
                    prefix,
                    child.getName(),
                    child.getNumber(),
                    System.lineSeparator()
            );
            showChildren(child, prefix);
        }
    }

    private void showChildren(Item item, String prefix) {
        prefix += "----";
        for (Item child : item.getChildren()) {
            System.out.printf(
                    "%s %s %s %s",
                    prefix,
                    child.getName(),
                    child.getNumber(),
                    System.lineSeparator()
            );
            showChildren(child, prefix);
        }
    }
}