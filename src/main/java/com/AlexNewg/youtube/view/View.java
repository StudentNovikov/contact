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
            menu(input);
            input = readConsoleString();
        }

        System.out.println(ConsoleMessages.FINISHED);
        memoryStorage.saveToStorage();
    }

    private void menu(String input) {

        switch (input) {
            case "1":
                System.out.println(ConsoleMessages.SHOW_ALL_CONTACTS);
                controller.printAllContacts();
                break;
            case "2":
                System.out.println(ConsoleMessages.SHOW_ALL_GROUPS);
                controller.printAllGroups();
                break;
            case "3":
                System.out.println(ConsoleMessages.UPDATE_CONTACT_DESCRIPTION);
                controller.printAllContacts();
                controller.updateContactDescription(readConsoleString());
                break;
            case "4":
                System.out.println(ConsoleMessages.DELETE_CONTACT);
                controller.printAllContacts();
                controller.deleteContact(readConsoleString());
                break;
            case "5":
                System.out.println(ConsoleMessages.ADD_GROUP_TO_CONTACT);
                controller.printAllContacts();
                controller.addGroupToContact(readConsoleString());
                break;
            case "6":
                System.out.println(ConsoleMessages.REMOVE_GROUP_FROM_CONTACT);
                controller.printAllContacts();
                controller.removeGroupFromContact(readConsoleString());
                break;
            case "7":
                System.out.println(ConsoleMessages.DETAILED_CONTACT);
                controller.printAllContacts();
                controller.showDetailedContact(readConsoleString());
                break;
            case "8":
                System.out.println(ConsoleMessages.CREATE_CONTACT);
                controller.createContact(readConsoleString());
                break;
            case "9":
                System.out.println(ConsoleMessages.SHOW_ALL_MEMBERS_OF_A_GROUP);
                controller.printAllGroups();
                controller.showAllMembersOfAGroup(readConsoleString());
                break;
            case "10":
                System.out.println(ConsoleMessages.UPDATE_CONTACT_NAME);
                controller.printAllContacts();
                controller.updateContactName(readConsoleString());
                break;
            case "11":
                System.out.println(ConsoleMessages.CREATE_GROUP);
                controller.printAllGroups();
                controller.createGroup(readConsoleString());
                break;
            case "12":
                System.out.println(ConsoleMessages.DELETE_GROUP);
                controller.printAllGroups();
                controller.deleteGroup(readConsoleString());
                break;
            case "13":
                System.out.println(ConsoleMessages.UPDATE_GROUP);
                controller.printAllGroups();
                controller.updateGroup(readConsoleString());
                break;
        }
        readConsoleString();
        System.out.println(ConsoleMessages.FULL_MENU);

    }

    private String readConsoleString() {
        return in.nextLine();
    }

}
