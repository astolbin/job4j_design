package ru.job4j.isp;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String read(String inputValue) {
        System.out.print(inputValue);
        return scanner.nextLine();
    }
}
