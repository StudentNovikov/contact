package com.AlexNewg.youtube.logic;


import com.AlexNewg.youtube.console.ui.WrongNameException;
import com.AlexNewg.youtube.dao.ContactDao;
import com.AlexNewg.youtube.dao.ContactDaoFactory;
import com.AlexNewg.youtube.model.Contact;

import java.util.List;

public class ContactLogic {

    private ContactDaoFactory contactDaoFactory = new ContactDaoFactory();
    private ContactDao service = (ContactDao) contactDaoFactory.createDao();

    public ContactLogic() {

    }

    public void createContact(String data) throws WrongNameException {
        String[] splited = data.split(" ");
        String name = splited[0];
        String description = data.substring(splited[0].length() + 1, data.length());
        validateContactName(name);
        service.create(new Contact(name, description));
    }

    private void validateContactName(String name) throws WrongNameException {
        if (!(name.length() < 40) && (name.length() > 0)) {
            throw new WrongNameException("wrong name: [" + name + "] it is too big");
        }
    }

    public void deleteContact(String name) {
        service.delete(name);
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
        return service.getAll();
    }

    public void updateContactName(String names) throws WrongNameException {
        String[] splited = names.split(" ");
        String oldName = splited[0];
        String newName = splited[1];
        validateContactName(newName);
        service.update(oldName, newName);
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