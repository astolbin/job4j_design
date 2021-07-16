package ru.job4j.isp;

public class MenuRunner {

    public void run(Item menu, Output out, Input in, Search search) {
        boolean run = true;
        while (run) {
            out.show(menu);
            String number =  in.read("PLease enter number of menu item in format X.X.X. or EXIT:"
                    + System.lineSeparator());
            if (number.equalsIgnoreCase("exit")) {
                run = false;
            } else {
                Item current = search.get(menu, number);
                if (current == null) {
                    System.out.println("Please enter correct number of menu");
                } else {
                    current.getAction().execute();
                }
                System.out.println("====================================");
            }
        }
    }

    public static void main(String[] args) {
        MenuRunner runner = new MenuRunner();
        Item menu = new MenuItem("0.", "Меню", new MenuAction("Выполнить Меню"));
        Item one = new MenuItem("1.", "Задача", new MenuAction("Выполняется задача 1."));
        Item oneOne = new MenuItem("1.1.", "Задача", new MenuAction("Выполняется задача 1.1."));
        Item oneOneOne = new MenuItem("1.1.1.", "Задача", new MenuAction("Выполняется задача 1.1.1."));
        Item oneOneTwo = new MenuItem("1.1.2.", "Задача", new MenuAction("Выполняется задача 1.1.2."));
        Item oneTwo = new MenuItem("1.2.", "Задача", new MenuAction("Выполняется задача 1.2."));
        menu.addChild(one);
        one.addChild(oneOne);
        one.addChild(oneTwo);
        oneOne.addChild(oneOneOne);
        oneOne.addChild(oneOneTwo);

        runner.run(menu, new ConsoleOutput(), new ConsoleInput(), new ItemSearch());
    }
}
