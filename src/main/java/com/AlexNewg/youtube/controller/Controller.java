package com.AlexNewg.youtube.controller;

import com.AlexNewg.youtube.dao.ContactDao;
import com.AlexNewg.youtube.dao.DaoFactory;
import com.AlexNewg.youtube.dao.GroupDao;
import com.AlexNewg.youtube.model.Contact;
import com.AlexNewg.youtube.model.Group;
import com.AlexNewg.youtube.view.*;

public class Controller {

    private static Controller instance;
    private ObserverOperator observerOperator;
    private DaoFactory daoFactory = new DaoFactory();
    private ContactDao contactDao = (ContactDao) daoFactory.createContactDao();
    private GroupDao groupDao = (GroupDao) daoFactory.createGroupDao();

    private Controller(ObserverOperator observerOperator) {
        this.observerOperator = observerOperator;
    }

    public static Controller getInstance(ObserverOperator observerOperator) {
        if (instance == null) {
            instance = new Controller(observerOperator);
        }
        return instance;
    }

    public void createContact(String data) {
        try {
            String[] split = data.split(" ");
            String name = split[0];
            String description = data.substring(split[0].length() + 1, data.length());
            validateContactName(name);
            contactDao.create(new Contact(name, description));
            observerOperator.notifyObserver();
        } catch (WrongNameException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printAllContacts() {
        for (Contact contact : contactDao.getAll()) {
            System.out.println(contact.toString());
        }
    }

    public void showDetailedContact(String name) {
        try {
            if (contactDao.readContact(name) != null) {
                System.out.println(contactDao.readContact(name).toStringDetailed());
            } else {
                throw new WrongNameException("There is no [ " + name + " ]");
            }
        } catch (WrongNameException e) {
            System.out.println(e.getMessage());
        }
    }


    public void updateContactName(String names) {
        try {
            String[] split = names.split(" ");
            String oldName = split[0];
            String newName = split[1];
            validateContactName(newName);
            contactDao.update(oldName, newName);
            observerOperator.notifyObserver();
        } catch (WrongNameException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateContactDescription(String input) {
        String[] split = input.split(" ");
        String name = split[0];
        String description = input.substring(split[0].length() + 1, input.length());
        contactDao.updateContactDescription(name, description);
        observerOperator.notifyObserver();
    }

    public void deleteContact(String name) {
        contactDao.delete(name);
        observerOperator.notifyObserver();
    }

    public void addGroupToContact(String data) {
        String[] split = data.split(" ");
        String name = split[0];
        String group = data.substring(split[0].length() + 1, data.length());
        contactDao.updateContactGroup(name, group);
        observerOperator.notifyObserver();
    }

    public void removeGroupFromContact(String data) {
        String[] split = data.split(" ");
        String name = split[0];
        String group = data.substring(split[0].length() + 1, data.length());
        contactDao.removeGroupFromContact(name, group);
        observerOperator.notifyObserver();
    }

    public void showAllMembersOfAGroup(String name) {
        for (Contact contact : contactDao.getAllMembersOfAGroup(name)) {
            System.out.println(contact.toString());
        }
    }

    public void createGroup(String name) {
        try {
            validateGroupName(name);
            groupDao.create(new Group(name));
        } catch (WrongNameException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printAllGroups() {
        for (Group group : groupDao.getAll()) {
            System.out.println(group.toString());
        }
    }

    public void deleteGroup(String groupName) {
        for (Contact contact : contactDao.getAll()) {
            contactDao.removeGroupFromContact(contact.getName(), groupName);
        }
        groupDao.delete(groupName);
        observerOperator.notifyObserver();
    }

    public void updateGroup(String names) {
        try {
            String[] split = names.split(" ");
            String oldName = split[0];
            String newName = split[1];
            validateGroupName(newName);
            groupDao.update(oldName, newName);
            observerOperator.notifyObserver();
        } catch (WrongNameException e) {
            System.out.println(e.getMessage());
        }
    }

    private void validateGroupName(String name) throws WrongNameException {
        if (!(name.length() < 20) && (name.length() > 0)) {
            throw new WrongNameException("wrong name: [" + name + "] it is too big");
        }
    }

    private void validateContactName(String name) throws WrongNameException {
        if (!(name.length() < 40) && (name.length() > 0)) {
            throw new WrongNameException("wrong name: [" + name + "] it is too big");
        }
    }

}
