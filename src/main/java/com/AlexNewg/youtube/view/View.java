package com.AlexNewg.youtube.view;

import com.AlexNewg.youtube.controller.Controller;
import com.AlexNewg.youtube.dao.MemoryStorage;

import java.util.Scanner;

public class View {

    private Scanner in = new Scanner(System.in);
    private Controller controller;
    private MemoryStorage memoryStorage;

    public View(Controller controller) {
        this.controller = controller;
        memoryStorage = MemoryStorage.getInstance();
    }

    public void start() {
        System.out.println(ConsoleMessages.FULL_MENU);
        String input = readConsoleString();
        while (!input.equals("0")) {
            controller.analyze(input);
            readConsoleString();
            System.out.println(ConsoleMessages.FULL_MENU);
            input = readConsoleString();
        }
        System.out.println(ConsoleMessages.FINISHED);
        memoryStorage.saveToStorage();
    }

    public String readConsoleString() {
        return in.nextLine();
    }

}
