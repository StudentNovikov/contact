package com.AlexNewg.youtube.model;

import com.AlexNewg.youtube.dao.DaoFactory;
import com.AlexNewg.youtube.view.WrongNameException;
import com.AlexNewg.youtube.dao.GroupDao;

import java.util.List;

public class GroupLogic {

    private DaoFactory daoFactory = new DaoFactory();
    private GroupDao groupDao = (GroupDao) daoFactory.createGroupDao();

    public List<Group> getAllGroups() {
        return groupDao.getAll();
    }

    public void createGroup(String name) throws WrongNameException {
        validateGroupName(name);
        groupDao.create(new Group(name));
    }

    private void validateGroupName(String name) throws WrongNameException {
        if (!(name.length() < 20) && (name.length() > 0)) {
            throw new WrongNameException("wrong name: [" + name + "] it is too big");
        }
    }

    public void deleteGroup(String name) {
        groupDao.delete(name);
    }

    public void updateGroup(String names) throws WrongNameException {
        String[] splited = names.split(" ");
        String oldName = splited[0];
        String newName = splited[1];
        validateGroupName(newName);
        groupDao.update(oldName, newName);
    }
}
