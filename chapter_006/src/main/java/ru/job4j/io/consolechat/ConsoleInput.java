package ru.job4j.io.consolechat;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private Scanner input = new Scanner(System.in);

    @Override
    public String ask() {
        return input.nextLine();
    }
}
