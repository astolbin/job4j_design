package ru.job4j.cache;

public class Emulator {
    public static void main(String[] args) {
        DirFileCache dir = new DirFileCache("./data/");

        System.out.println(dir.get("Names.txt"));
        System.out.println(dir.get("Address.txt"));
    }
}
