package com.AlexNewg.youtube.view;

final class ConsoleMessages {

    static final String FULL_MENU = "Choose one of the following:" + "\n" +
            " 1. Show all contacts " + "\n" +
            " 2. Show all groups " + "\n" +
            " 3. Change the description of a contact " + "\n" +
            " 4. Delete an existing contact " + "\n" +
            " 5. Add a group to an existing contact " + "\n" +
            " 6. Delete a group from a contact " + "\n" +
            " 7. Show detailed info about contact " + "\n" +
            " 8. Create a new contact " + "\n" +
            " 9. Show all contacts with specific group" + "\n" +
            " 10. Change the name of a contact " + "\n" +
            " 11. Create a new group " + "\n" +
            " 12. Delete an existing group " + "\n" +
            " 13. Update an existing group" + "\n" +
            " 0. Exit ";

    static final String CHOOSE_PARSING_TYPE = "Choose the parsing method tou want to use:" + "\n" +
            " 1. DOM " + "\n" +
            " 2. SAX " + "\n" +
            " 3. Jackson " + "\n" +
            " 4. Default " + "\n";

    static final String SHOW_ALL_MEMBERS_OF_A_GROUP = "Choose the name of a group, which members you want to see";
    static final String REMOVE_GROUP_FROM_CONTACT = "Choose the name of a contact, then after space the group you want to delete";
    static final String ADD_GROUP_TO_CONTACT = "Choose the name of a contact, then after space type what group to add for it";
    static final String DELETE_CONTACT = "Choose the name of a contact you want to delete";
    static final String UPDATE_CONTACT_DESCRIPTION = "Choose the name of a contact, which description you would like to update and put a new description after a space: ";
    static final String UPDATE_CONTACT_NAME = "Choose the name of a contact you would like to update and put a new name after a space: ";
    static final String CREATE_CONTACT = "Input new contact name and description, split by a space";
    static final String DETAILED_CONTACT = "Choose the name of a contact you would like to see in details";
    static final String UPDATE_GROUP = "Choose the name of a group you would like to update and put a new name after a space: ";
    static final String DELETE_GROUP = "Choose the name of a group you would like to delete: ";
    static final String CREATE_GROUP = "Input new group name";
    static final String FINISHED = "Thanks for using our app";
    static final String SHOW_ALL_CONTACTS = "All contacts: ";
    static final String SHOW_ALL_GROUPS = "All groups: ";


}
