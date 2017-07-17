package com.AlexNewg.youtube.controller;

import com.AlexNewg.youtube.console.ui.State;
import com.AlexNewg.youtube.console.ui.WrongNameException;
import com.AlexNewg.youtube.logic.GroupLogic;
import com.AlexNewg.youtube.model.Group;

import java.util.Scanner;

import static com.AlexNewg.youtube.console.ui.State.*;

/**
 * Created by Alex on 16.07.2017.
 */
public class GroupController {

    private final String MENU = "Choose one of the following:" + "\n" +
            " 1. Show all groups " + "\n" +
            " 2. Create a new group " + "\n" +
            " 3. Delete an existing group " + "\n" +
            " 4. Update an existing group" + "\n" +
            " 0. Main menu ";

    private GroupLogic groupLogic;

    public GroupController(GroupLogic groupLogic) {
        this.groupLogic = groupLogic;
    }

    public void showMenu() {
        System.out.println(MENU);
    }

    public State processInput(String input) {
        switch (input) {
            case "1":
                return printAllGroups();
            case "2":
                return showCreateGroupMenu();
            case "3":
                return showDeleteGroupMenu();
            case "4":
                return showUpdateGroupMenu();
            case "0":
                return MAIN_MENU;
            default:
                return UNKNOWN;
        }
    }

    private State showUpdateGroupMenu() {
        System.out.println("Choose the name of a group you would like to update and put a new name after a space: ");
        printAllGroups();
        return UPDATE_GROUP;
    }

    private State showDeleteGroupMenu() {
        System.out.println("Choose the name of a group you would like to delete: ");
        printAllGroups();
        return DELETE_GROUP;
    }

    private State showCreateGroupMenu() {
        System.out.println("Input new group name");
        return CREATE_GROUP;
    }

    public State createGroup(String name) {
        try {
            groupLogic.createGroup(name);
        } catch (WrongNameException e) {
            System.out.println(e.getMessage());
        }
        return MAIN_MENU;
    }

    private State printAllGroups() {
        for (Group group : groupLogic.getAllGroups()) {
            System.out.println(group.toString());
        }
        return MAIN_MENU;
    }

    public State deleteGroup(String name) {
        groupLogic.deleteGroup(name);
        return MAIN_MENU;
    }

    public State updateGroup(String names) {
        try {
            groupLogic.updateGroup(names);
        } catch (WrongNameException e) {
            System.out.println(e.getMessage());
        }
        return MAIN_MENU;
    }
}
