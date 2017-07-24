package com.AlexNewg.youtube.model;

import com.AlexNewg.youtube.dao.DaoFactory;
import com.AlexNewg.youtube.view.WrongNameException;
import com.AlexNewg.youtube.dao.ContactDao;

import java.util.List;

public class ContactLogic {

    private DaoFactory daoFactory = new DaoFactory();
    private ContactDao contactDao = (ContactDao) daoFactory.createContactDao();

    public void createContact(String data) throws WrongNameException {
        String[] split = data.split(" ");
        String name = split[0];
        String description = data.substring(split[0].length() + 1, data.length());
        validateContactName(name);
        contactDao.create(new Contact(name, description));
    }

    private void validateContactName(String name) throws WrongNameException {
        if (!(name.length() < 40) && (name.length() > 0)) {
            throw new WrongNameException("wrong name: [" + name + "] it is too big");
        }
    }

    public void deleteContact(String name) {
        contactDao.delete(name);
    }

    public void addGroupInContact(String data) {
        String[] split = data.split(" ");
        String name = split[0];
        String group = data.substring(split[0].length() + 1, data.length());
        contactDao.updateContactGroup(name, group);
    }

    public void removeGroupFromContact(String data) {
        String[] split = data.split(" ");
        String name = split[0];
        String group = data.substring(split[0].length() + 1, data.length());
        contactDao.removeGroupFromContact(name, group);
    }

    public Contact getContact(String name) throws WrongNameException {
        if (contactDao.readContact(name) != null) {
            return contactDao.readContact(name);
        } else {
            throw new WrongNameException("There is no [ " + name + " ]");
        }
    }

    public List<Contact> getAllContacts() {
        return contactDao.getAll();
    }

    public void updateContactName(String names) throws WrongNameException {
        String[] split = names.split(" ");
        String oldName = split[0];
        String newName = split[1];
        validateContactName(newName);
        contactDao.update(oldName, newName);
    }

    public void updateContactDescription(String data) {
        String[] split = data.split(" ");
        String name = split[0];
        String description = data.substring(split[0].length() + 1, data.length());
        contactDao.updateContactDescription(name, description);
    }

    public List<Contact> getAllMembersOfAGroup(String name) {
        return contactDao.getAllMembersOfAGroup(name);
    }
}