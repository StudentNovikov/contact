package com.AlexNewg.youtube.controller;

import com.AlexNewg.youtube.console.ui.State;
import com.AlexNewg.youtube.console.ui.WrongNameException;
import com.AlexNewg.youtube.logic.ContactLogic;
import com.AlexNewg.youtube.model.Contact;

import static com.AlexNewg.youtube.console.ui.State.*;

/**
 * Created by Alex on 16.07.2017.
 */
public class ContactController {

    private static final String MENU = "Choose one of the following:" + "\n" +
            " 1. Create a new contact " + "\n" +
            " 2. Change the name of a contact " + "\n" +
            " 3. Change the description of a contact " + "\n" +
            " 4. Delete an existing contact " + "\n" +
            " 5. Add a group to an existing contact " + "\n" +
            " 6. Delete a group from a contact " + "\n" +
            " 7. Show detailed info about contact " + "\n" +
            " 8. Show all contacts " + "\n" +
            " 9. Show all contacts with specific group" + "\n" +
            " 0. Main menu";

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
        System.out.println("Choose the name of a group, which members you want to see");
        printAllContacts();
        return SHOW_ALL_MEMBERS_OF_A_GROUP;
    }

    private State showDeleteGroupFromContactMenu() {
        System.out.println("Choose the name of a contact, then after space the group you want to delete");
        printAllContacts();
        return REMOVE_GROUP_FROM_CONTACT;
    }


    private State showAddingGroupToContactMenu() {
        System.out.println("Choose the name of a contact, then after space type what group to add for it");
        printAllContacts();
        return ADD_GROUP_TO_CONTACT;
    }

    private State showDeleteContactMenu() {
        System.out.println("Choose the name of a contact you want to delete: ");
        printAllContacts();

        return DELETE_CONTACT;
    }

    private State showUpdateContactDescriptionMenu() {
        System.out.println("Choose the name of a contact, which description you would like to update and put a new description after a space: ");
        printAllContacts();
        return UPDATE_CONTACT_DESCRIPTION;
    }

    private State showUpdateContactNameMenu() {
        System.out.println("Choose the name of a contact you would like to update and put a new name after a space: ");
        printAllContacts();
        return UPDATE_CONTACT_NAME;
    }

    private State showCreateContactMenu() {
        System.out.println("Input new contact name and description, splitted my a space");
        return CREATE_CONTACT;
    }

    private State showDetailedContactMenu() {
        System.out.println("Choose the name of a contact you would like to see in details");
        printAllContacts();
        return DETAILED_CONTACT;
    }

    private void printAllContacts() {
        for (Contact contact : contactLogic.getAllContacts()) {
            System.out.println(contact.toString());
        }
    }

    public void showMenu() {
        System.out.println(MENU);
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
