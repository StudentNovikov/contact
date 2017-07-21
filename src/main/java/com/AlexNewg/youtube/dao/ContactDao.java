package com.AlexNewg.youtube.dao;



import com.AlexNewg.youtube.model.Contact;

import java.util.List;

public class ContactDao implements IDao<Contact> {

    private final MemoryStorage storage = MemoryStorage.getInstance();

    public List<Contact> getAll() {
        return storage.getAllContacts();
    }

    public Contact readContact(String name) {
        return storage.getContactByName(name);
    }

    public void create(Contact contact) {
        storage.createContact(contact);
    }

    public void update(String oldName, String newName) {
        storage.updateContactName(oldName, newName);
    }

    public void updateContactDescription(String name, String description) {
        storage.updateContactDescription(name, description);
    }

    public void delete(String name) {
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
