package com.AlexNewg.youtube.controller;

import com.AlexNewg.youtube.model.ContactLogic;
import com.AlexNewg.youtube.model.GroupLogic;
import com.AlexNewg.youtube.model.Contact;
import com.AlexNewg.youtube.model.Group;
import com.AlexNewg.youtube.view.*;

public class Controller {

    private static Controller instance;
    private View view = new View(instance);
    private ContactLogic contactLogic = new ContactLogic();
    private GroupLogic groupLogic = new GroupLogic();
    private ObserverOperator observerOperator ;


    private Controller(ObserverOperator observerOperator) {
        this.observerOperator = observerOperator;
    }

    public static Controller getInstance(ObserverOperator observerOperator) {
        if (instance == null) {
            instance = new Controller(observerOperator);
        }
        return instance;
    }

    public void analyze(String input) {
        switch (input) {
            case "1":
                System.out.println(ConsoleMessages.SHOW_ALL_CONTACTS);
                printAllContacts();
                break;
            case "2":
                System.out.println(ConsoleMessages.SHOW_ALL_GROUPS);
                printAllGroups();
                break;
            case "3":
                System.out.println(ConsoleMessages.UPDATE_CONTACT_DESCRIPTION);
                printAllContacts();
                updateContactDescription(view.readConsoleString());
                observerOperator.notifyObserver();
                break;
            case "4":
                System.out.println(ConsoleMessages.DELETE_CONTACT);
                printAllContacts();
                deleteContact(view.readConsoleString());
                observerOperator.notifyObserver();
                break;
            case "5":
                System.out.println(ConsoleMessages.ADD_GROUP_TO_CONTACT);
                printAllContacts();
                addGroupToContact(view.readConsoleString());
                observerOperator.notifyObserver();
                break;
            case "6":
                System.out.println(ConsoleMessages.REMOVE_GROUP_FROM_CONTACT);
                printAllContacts();
                removeGroupFromContact(view.readConsoleString());
                observerOperator.notifyObserver();
                break;
            case "7":
                System.out.println(ConsoleMessages.DETAILED_CONTACT);
                printAllContacts();
                showDetailedContact(view.readConsoleString());
                break;
            case "8":
                System.out.println(ConsoleMessages.CREATE_CONTACT);
                createContact(view.readConsoleString());
                observerOperator.notifyObserver();
                break;
            case "9":
                System.out.println(ConsoleMessages.SHOW_ALL_MEMBERS_OF_A_GROUP);
                printAllGroups();
                showAllMembersOfAGroup(view.readConsoleString());
                break;
            case "10":
                System.out.println(ConsoleMessages.UPDATE_CONTACT_NAME);
                printAllContacts();
                updateContactName(view.readConsoleString());
                observerOperator.notifyObserver();
                break;
            case "11":
                System.out.println(ConsoleMessages.CREATE_GROUP);
                createGroup(view.readConsoleString());
                break;
            case "12":
                System.out.println(ConsoleMessages.DELETE_GROUP);
                deleteGroup(view.readConsoleString());
                break;
            case "13":
                System.out.println(ConsoleMessages.UPDATE_GROUP);
                updateGroup(view.readConsoleString());
                break;
        }

    }

    private void createContact(String name) {
        try {
            contactLogic.createContact(name);
        } catch (WrongNameException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printAllContacts() {
        for (Contact contact : contactLogic.getAllContacts()) {
            System.out.println(contact.toString());
        }
    }

    private void showDetailedContact(String name) {
        try {
            System.out.println(contactLogic.getContact(name).toStringDetailed());
        } catch (WrongNameException e) {
            System.out.println(e.getMessage());
        }
    }


    private void updateContactName(String names) {
        try {
            contactLogic.updateContactName(names);
        } catch (WrongNameException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateContactDescription(String input) {
        contactLogic.updateContactDescription(input);
    }

    private void deleteContact(String name) {
        contactLogic.deleteContact(name);
    }

    private void addGroupToContact(String data) {
        contactLogic.addGroupInContact(data);
    }

    private void removeGroupFromContact(String data) {
        contactLogic.removeGroupFromContact(data);
    }

    private void showAllMembersOfAGroup(String name) {
        for (Contact contact : contactLogic.getAllMembersOfAGroup(name)) {
            System.out.println(contact.toString());
        }
    }

    private void createGroup(String name) {
        try {
            groupLogic.createGroup(name);
        } catch (WrongNameException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printAllGroups() {
        for (Group group : groupLogic.getAllGroups()) {
            System.out.println(group.toString());
        }
    }

    private void deleteGroup(String name) {
        groupLogic.deleteGroup(name);
    }

    private void updateGroup(String names) {
        try {
            groupLogic.updateGroup(names);
        } catch (WrongNameException e) {
            System.out.println(e.getMessage());
        }
    }
}
