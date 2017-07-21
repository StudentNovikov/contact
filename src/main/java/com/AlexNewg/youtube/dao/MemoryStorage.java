package com.AlexNewg.youtube.dao;


import com.AlexNewg.youtube.model.Contact;
import com.AlexNewg.youtube.model.Group;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MemoryStorage {

    private static MemoryStorage Instance;

    private MemoryStorage() {
        initStartingData();
    }

    public static MemoryStorage getInstance() {
        if (Instance == null) {
            Instance = new MemoryStorage();
        }
        return Instance;
    }


    private List<Contact> allContacts = new ArrayList<>();
    private List<Group> allGroups = new ArrayList<>();


    private void initStartingData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("storage.dat"))) {
            allContacts = (List<Contact>) ois.readObject();
            allGroups = (List<Group>) ois.readObject();
        } catch (Exception ex) {
            System.out.println("Exception while loading file: " + ex.getMessage());
        }
    }

    public List<Contact> getAllContacts() {
        return allContacts;
    }

    public List<Group> getAllGroups() {
        return allGroups;
    }

    public int getMaxGroupId() {
        if (allGroups.isEmpty()) {
            return 1;
        }
        return allGroups.get(allGroups.size() - 1).getId() + 1;
    }

    public int getMaxContactId() {
        if (allContacts.isEmpty()) {
            return 1;
        }
        return allContacts.get(allContacts.size() - 1).getId() + 1;
    }

    public void createGroup(Group group) {
        group.setId(getMaxGroupId());
        allGroups.add(group);

    }

    public void deleteGroup(String name) {

        for (int i = 0; i < allGroups.size(); i++) {
            if (allGroups.get(i).getName().equals(name)) {
                allGroups.remove(i--);
            }
        }
    }

    public void updateGroup(String oldName, String newName) {

        for (int i = 0; i < allGroups.size(); i++) {
            if (allGroups.get(i).getName().equals(oldName)) {
                allGroups.get(i).setName(newName);
            }
        }
    }

    public Contact getContactByName(String name) {
        for (int i = 0; i < allContacts.size(); i++) {
            if (allContacts.get(i).getName().equals(name)) {
                return allContacts.get(i);
            }
        }
        return null;
    }

    public void createContact(Contact contact) {
        contact.setId(getMaxContactId());
        contact.setName(contact.getName());
        contact.setDescription(contact.getDescription());
        allContacts.add(contact);
    }

    public void updateContactName(String oldName, String newName) {
        for (int i = 0; i < allContacts.size(); i++) {
            if (allContacts.get(i).getName().equals(oldName)) {
                allContacts.get(i).setName(newName);
                return;
            }
        }
    }

    public void updateContactDescription(String name, String description) {
        for (int i = 0; i < allContacts.size(); i++) {
            if (allContacts.get(i).getName().equals(name)) {
                allContacts.get(i).setDescription(description);
                return;
            }
        }
    }

    public void deleteContact(String name) {
        for (int i = 0; i < allContacts.size(); i++) {
            if (allContacts.get(i).getName().equals(name)) {
                allContacts.remove(i);
                return;
            }
        }
    }

    public void updateContactGroup(String name, String group) {
        for (int i = 0; i < allContacts.size(); i++) {
            if (allContacts.get(i).getName().equals(name)) {
                if (doesThisGroupExists(group) >= 0) {
                    if (!isGroupAlreadyInContact(allContacts.get(i), group)) {
                        allContacts.get(i).addGroup(allGroups.get(doesThisGroupExists(group)));
                        return;
                    }
                }
            }
        }
    }

    private int doesThisGroupExists(String group) {
        for (int i = 0; i < allGroups.size(); i++) {
            if (allGroups.get(i).getName().equals(group)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isGroupAlreadyInContact(Contact contact, String group) {
        List<Group> groups = contact.getGroups();
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getName().equals(group)) {
                return true;
            }
        }
        return false;
    }

    public void removeGroupFromContact(String name, String group) {
        for (int i = 0; i < allContacts.size(); i++) {
            if (allContacts.get(i).getName().equals(name)) {
                List<Group> groups = allContacts.get(i).getGroups();
                for (int j = 0; j < groups.size(); j++) {
                    if (groups.get(j).getName().equals(group)) {
                        groups.remove(j);
                        return;
                    }
                }
            }
        }
    }

    public List<Contact> getAllMembersOfAGroup(String name) {
        List<Contact> contacts = new ArrayList<>();
        for (int i = 0; i < allContacts.size(); i++) {
            List<Group> groups = allContacts.get(i).getGroups();
            for (Group group : groups) {
                if (group.getName().equals(name)) {
                    contacts.add(allContacts.get(i));
                }
            }
        }
        return contacts;
    }

    public void saveToStorage() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("storage.dat"))) {
            oos.writeObject(allContacts);
            oos.writeObject(allGroups);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
