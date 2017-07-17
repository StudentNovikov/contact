package com.AlexNewg.youtube.controller;

import com.AlexNewg.youtube.console.ui.ConsoleMessages;
import com.AlexNewg.youtube.console.ui.State;

import static com.AlexNewg.youtube.console.ui.State.*;

public class MainMenuController implements IController{

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
                System.out.println(ConsoleMessages.FINISHED);
                return FINISHED;
            default:
                System.out.println("input command [" + input + "] is not supported");
                return MAIN_MENU;
        }
    }

    public void showMenu() {
        System.out.println(ConsoleMessages.MAIN_MENU);
    }
}
