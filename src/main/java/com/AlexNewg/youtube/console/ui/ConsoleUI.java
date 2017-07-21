package com.AlexNewg.youtube.console.ui;

import com.AlexNewg.youtube.controller.ContactController;
import com.AlexNewg.youtube.controller.GroupController;
import com.AlexNewg.youtube.controller.MainMenuController;
import com.AlexNewg.youtube.dao.MemoryStorage;
import com.AlexNewg.youtube.logic.ContactLogic;
import com.AlexNewg.youtube.logic.GroupLogic;

import java.util.Scanner;

public class ConsoleUI {


    private Scanner in = new Scanner(System.in);
    private MainMenuController mainMenuController = new MainMenuController();
    private ContactController contactController;
    private GroupController groupController;
    private MemoryStorage memoryStorage;

    public ConsoleUI() {
        memoryStorage = MemoryStorage.getInstance();
        contactController = new ContactController(new ContactLogic());
        groupController = new GroupController(new GroupLogic());
    }

    public void start() {
        State state = State.STARTED;
        while (state != State.FINISHED) {
            showMenu(state);
            String input = readConsoleString();
            state = processInput(state, input);
        }
        memoryStorage.saveToStorage();
    }

    private State processInput(State state, String input) {
        switch (state) {
            case STARTED:
            case MAIN_MENU:
                return mainMenuController.processInput(input);
            case CONTACT_MENU:
                return contactController.processInput(input);
            case GROUP_MENU:
                return groupController.processInput(input);
            case CREATE_GROUP:
                return groupController.createGroup(input);
            case DELETE_GROUP:
                return groupController.deleteGroup(input);
            case UPDATE_GROUP:
                return groupController.updateGroup(input);
            case DETAILED_CONTACT:
                return contactController.showDetailedContact(input);
            case CREATE_CONTACT:
                return contactController.createContact(input);
            case UPDATE_CONTACT_NAME:
                return contactController.updateContactName(input);
            case UPDATE_CONTACT_DESCRIPTION:
                return contactController.updateContactDescription(input);
            case DELETE_CONTACT:
                return contactController.deleteContact(input);
            case ADD_GROUP_TO_CONTACT:
                return contactController.addGroupToContact(input);
            case REMOVE_GROUP_FROM_CONTACT:
                return contactController.removeGroupFromContact(input);
            case SHOW_ALL_MEMBERS_OF_A_GROUP:
                return contactController.showAllMembersOfAGroup(input);
            default:
                System.out.println("smth went terribly wrong");
        }
        return state;
    }

    private void showMenu(State state) {
        switch (state) {
            case STARTED:
            case UNKNOWN:
            case MAIN_MENU:
                mainMenuController.showMenu();
                break;
            case CONTACT_MENU:
                contactController.showMenu();
                break;
            case GROUP_MENU:
                groupController.showMenu();
                break;
            case CREATE_GROUP:
            case DELETE_GROUP:
            case UPDATE_GROUP:
            case DETAILED_CONTACT:
            case CREATE_CONTACT:
            case UPDATE_CONTACT_NAME:
            case UPDATE_CONTACT_DESCRIPTION:
            case DELETE_CONTACT:
            case ADD_GROUP_TO_CONTACT:
            case REMOVE_GROUP_FROM_CONTACT:
            case SHOW_ALL_MEMBERS_OF_A_GROUP:
                break;
            default:
                System.out.println("unexpected behavior");
        }
    }

    private String readConsoleString() {
        return in.nextLine();
    }
}
