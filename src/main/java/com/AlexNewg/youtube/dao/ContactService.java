package com.AlexNewg.youtube.dao;



import com.AlexNewg.youtube.model.Contact;

import java.util.List;

/**
 * Created by Alex on 12.07.2017.
 */
public class ContactService {

    private final MemoryStorage storage;

    public ContactService(MemoryStorage memoryStorage) {
        this.storage = memoryStorage;
    }

    public List<Contact> GetAllContacts() {
        return storage.getAllContacts();
    }

    public Contact readContact(String name) {
        return storage.getContactByName(name);
    }

    public void createContact(Contact contact) {
        storage.createContact(contact);
    }

    public void updateContactName(String oldName, String newName) {
        storage.updateContactName(oldName, newName);
    }

    public void updateContactDescription(String name, String description) {
        storage.updateContactDescription(name, description);
    }

    public void deleteContact(String name) {
        storage.deleteContact(name);
    }

    public void updateContactGroup(String name, String group) {
        storage.updateContactGroup(name, group);
    }

    public void removeGroupFromContact(String name, String group) {
        storage.removeGroupFromContact(name, group);
    }

    public List<Contact> getAllMembersOfAGroup(String name) {
        return storage.getAllMembersOfAGroup(name);
    }
}
