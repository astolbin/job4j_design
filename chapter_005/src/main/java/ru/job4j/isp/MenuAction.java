package ru.job4j.isp;

import java.util.Objects;

public class MenuAction implements Action {
    private final String nameAction;

    public MenuAction(String nameAction) {
        this.nameAction = nameAction;
    }

    @Override
    public void execute() {
        System.out.println(nameAction);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MenuAction that = (MenuAction) o;
        return Objects.equals(nameAction, that.nameAction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameAction);
    }
}
