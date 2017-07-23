package com.AlexNewg.youtube.dao;


import com.AlexNewg.youtube.model.Contact;
import com.AlexNewg.youtube.model.Group;

import java.util.ArrayList;
import java.util.List;

public class ContactDao implements IDao<Contact> {

    private final MemoryStorage storage = MemoryStorage.getInstance();
    private List<Contact> allContacts = storage.getAllContacts();
    private List<Group> allGroups = storage.getAllGroups();

    public List<Contact> getAll() {
        return allContacts;
    }

    public Contact readContact(String name) {
        for (int i = 0; i < allContacts.size(); i++) {
            if (allContacts.get(i).getName().equals(name)) {
                return allContacts.get(i);
            }
        }
        return null;
    }

    public void create(Contact contact) {
        contact.setId(getMaxContactId());
        contact.setName(contact.getName());
        contact.setDescription(contact.getDescription());
        allContacts.add(contact);
    }

    private int getMaxContactId() {
        if (allContacts.isEmpty()) {
            return 1;
        }
        return allContacts.get(allContacts.size() - 1).getId() + 1;
    }

    public void update(String oldName, String newName) {
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

    public void delete(String name) {
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
}
