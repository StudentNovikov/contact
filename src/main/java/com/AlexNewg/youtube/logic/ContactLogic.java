package com.AlexNewg.youtube.logic;


import com.AlexNewg.youtube.console.ui.WrongNameException;
import com.AlexNewg.youtube.dao.ContactService;
import com.AlexNewg.youtube.dao.MemoryStorage;
import com.AlexNewg.youtube.model.Contact;

import java.util.List;

/**
 * Created by Alex on 12.07.2017.
 */
public class ContactLogic {

    private ContactService service;

    public ContactLogic(MemoryStorage memoryStorage) {
        service = new ContactService(memoryStorage);
    }

    public void createContact(String data) throws WrongNameException {
        String[] splited = data.split(" ");
        String name = splited[0];
        String description = data.substring(splited[0].length() + 1, data.length());
        validateContactName(name);
        service.createContact(new Contact(name, description));

    }

    private void validateContactName(String name) throws WrongNameException {
        if ((name.length() < 40) && (name.length() > 0)) {
            return;
        } else {
            throw new WrongNameException("wrong name: [" + name + "] it is too big");
        }
    }

    public void deleteContact(String name) {
        service.deleteContact(name);
    }

    public void addGroupInContact(String data) {
        String[] splited = data.split(" ");
        String name = splited[0];
        String group = data.substring(splited[0].length() + 1, data.length());
        service.updateContactGroup(name, group);
    }

    public void removeGroupFromContact(String data) {
        String[] splited = data.split(" ");
        String name = splited[0];
        String group = data.substring(splited[0].length() + 1, data.length());
        service.removeGroupFromContact(name, group);
    }

    public Contact getContact(String name) throws WrongNameException {
        if (service.readContact(name) != null) {
            return service.readContact(name);
        } else {
            throw new WrongNameException("There is no [ " + name + " ]");
        }
    }

    public List<Contact> getAllContacts() {
        return service.GetAllContacts();
    }

    public void updateContactName(String names) throws WrongNameException {
        String[] splited = names.split(" ");
        String oldName = splited[0];
        String newName = splited[1];
        validateContactName(newName);
        service.updateContactName(oldName, newName);
    }

    public void updateContactDescription(String data) {
        String[] splited = data.split(" ");
        String name = splited[0];
        String description = data.substring(splited[0].length() + 1, data.length());
        service.updateContactDescription(name, description);
    }

    public List<Contact> getAllMembersOfAGroup(String name) {
        return service.getAllMembersOfAGroup(name);
    }
}