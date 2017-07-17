package com.AlexNewg.youtube.controller;

import com.AlexNewg.youtube.console.ui.State;

import static com.AlexNewg.youtube.console.ui.State.*;

/**
 * Created by Alex on 16.07.2017.
 */
public class MainMenuController {

    private static final String MENU = "Choose what do you want to work with:" + "\n" +
            " 1. Contacts" + "\n" +
            " 2. Groups" + "\n" +
            " 3. Exit";

    /**
     * this method completes analitics on users input
     */
    public State processInput(String input) {
        switch (input) {
            case "1":
                return CONTACT_MENU;
            case "2":
                return GROUP_MENU;
            case "3":
                System.out.println("thanks for using our app");
                return FINISHED;
            default:
                System.out.println("input command [" + input + "] is not supported");
                return MAIN_MENU;
        }
    }

    public void showMenu() {
        System.out.println(MENU);
    }
}
