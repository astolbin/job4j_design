package ru.job4j.isp;

public class StubOutput implements Output {
    private final StringBuilder buffer = new StringBuilder();

    @Override
    public void show(Item root) {
        String prefix = "";
        for (Item child : root.getChildren()) {
            buffer.append(String.format("%s %s %s %s", prefix, child.getName(),
                    child.getNumber(), System.lineSeparator()));
            showChildren(child, prefix);
        }
    }

    private void showChildren(Item item, String prefix) {
        prefix += "----";
        for (Item child : item.getChildren()) {
            buffer.append(String.format("%s %s %s %s", prefix, child.getName(),
                    child.getNumber(), System.lineSeparator()));
            showChildren(child, prefix);
        }
    }

    @Override
    public String toString() {
        return buffer.toString();
    }
}
