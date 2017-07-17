package com.AlexNewg.youtube.logic;


import com.AlexNewg.youtube.console.ui.WrongNameException;
import com.AlexNewg.youtube.dao.GroupService;
import com.AlexNewg.youtube.dao.MemoryStorage;
import com.AlexNewg.youtube.model.Group;

import java.util.List;

/**
 * Created by Alex on 12.07.2017.
 */
public class GroupLogic {

    private GroupService service;

    public GroupLogic(MemoryStorage memoryStorage) {
        service = new GroupService(memoryStorage);
    }

    public List<Group> getAllGroups() {
        return service.getAllGroups();
    }

    public void createGroup(String name) throws WrongNameException {
        validateGroupName(name);
        service.createGroup(new Group(name));
    }

    private void validateGroupName(String name) throws WrongNameException {
        if ((name.length() < 20) && (name.length() > 0)) {
            return;
        } else {
            throw new WrongNameException("wrong name: [" + name + "] it is too big");
        }
    }

    public void deleteGroup(String name) {
        service.deleteGroup(new Group(name));
    }

    public void updateGroup(String names) throws WrongNameException {
        String[] splited = names.split(" ");
        String oldName = splited[0];
        String newName = splited[1];
        validateGroupName(newName);
        service.updateGroup(new Group(oldName), new Group(newName));
    }
}
