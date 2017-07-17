package com.AlexNewg.youtube.controller;

import com.AlexNewg.youtube.console.ui.ConsoleMessages;
import com.AlexNewg.youtube.console.ui.State;
import com.AlexNewg.youtube.console.ui.WrongNameException;
import com.AlexNewg.youtube.logic.ContactLogic;
import com.AlexNewg.youtube.model.Contact;

import static com.AlexNewg.youtube.console.ui.State.*;

public class ContactController implements IController {

    private ContactLogic contactLogic;

    public ContactController(ContactLogic contactLogic) {
        this.contactLogic = contactLogic;
    }

    public State processInput(String input) {
        switch (input) {
            case "1":
                return showCreateContactMenu();
            case "2":
                return showUpdateContactNameMenu();
            case "3":
                return showUpdateContactDescriptionMenu();
            case "4":
                return showDeleteContactMenu();
            case "5":
                return showAddingGroupToContactMenu();
            case "6":
                return showDeleteGroupFromContactMenu();
            case "7":
                return showDetailedContactMenu();
            case "8":
                printAllContacts();
                return MAIN_MENU;
            case "9":
                return showAllContactWithSpecificGroupMenu();
            case "0":
                return MAIN_MENU;
            default:
                return UNKNOWN;
        }
    }

    private State showAllContactWithSpecificGroupMenu() {
        System.out.println(ConsoleMessages.SHOW_ALL_MEMBERS_OF_A_GROUP);
        printAllContacts();
        return SHOW_ALL_MEMBERS_OF_A_GROUP;
    }

    private State showDeleteGroupFromContactMenu() {
        System.out.println(ConsoleMessages.REMOVE_GROUP_FROM_CONTACT);
        printAllContacts();
        return REMOVE_GROUP_FROM_CONTACT;
    }


    private State showAddingGroupToContactMenu() {
        System.out.println(ConsoleMessages.ADD_GROUP_TO_CONTACT);
        printAllContacts();
        return ADD_GROUP_TO_CONTACT;
    }

    private State showDeleteContactMenu() {
        System.out.println(ConsoleMessages.DELETE_CONTACT);
        printAllContacts();

        return DELETE_CONTACT;
    }

    private State showUpdateContactDescriptionMenu() {
        System.out.println(ConsoleMessages.UPDATE_CONTACT_DESCRIPTION);
        printAllContacts();
        return UPDATE_CONTACT_DESCRIPTION;
    }

    private State showUpdateContactNameMenu() {
        System.out.println(ConsoleMessages.UPDATE_CONTACT_NAME);
        printAllContacts();
        return UPDATE_CONTACT_NAME;
    }

    private State showCreateContactMenu() {
        System.out.println(ConsoleMessages.CREATE_CONTACT);
        return CREATE_CONTACT;
    }

    private State showDetailedContactMenu() {
        System.out.println(ConsoleMessages.DETAILED_CONTACT);
        printAllContacts();
        return DETAILED_CONTACT;
    }

    private void printAllContacts() {
        for (Contact contact : contactLogic.getAllContacts()) {
            System.out.println(contact.toString());
        }
    }

    public void showMenu() {
        System.out.println(ConsoleMessages.CONTACT_MENU);
    }

    public State showDetailedContact(String name) {
        try {
            System.out.println(contactLogic.getContact(name).toStringDetailed());
        } catch (WrongNameException e) {
            System.out.println(e.getMessage());
        }
        return MAIN_MENU;
    }

    public State createContact(String input) {
        try {
            contactLogic.createContact(input);
        } catch (WrongNameException e) {
            System.out.println(e.getMessage());
        }
        return MAIN_MENU;
    }

    public State updateContactName(String names) {
        try {
            contactLogic.updateContactName(names);
        } catch (WrongNameException e) {
            System.out.println(e.getMessage());
        }
        return MAIN_MENU;
    }

    public State updateContactDescription(String input) {
        contactLogic.updateContactDescription(input);
        return MAIN_MENU;
    }

    public State deleteContact(String name) {
        contactLogic.deleteContact(name);
        return MAIN_MENU;
    }

    public State addGroupToContact(String data) {
        contactLogic.addGroupInContact(data);
        return MAIN_MENU;
    }

    public State removeGroupFromContact(String data) {
        contactLogic.removeGroupFromContact(data);
        return MAIN_MENU;
    }

    public State showAllMembersOfAGroup(String name) {
        for (Contact contact : contactLogic.getAllMembersOfAGroup(name)) {
            System.out.println(contact.toString());
        }
        return MAIN_MENU;
    }
}
