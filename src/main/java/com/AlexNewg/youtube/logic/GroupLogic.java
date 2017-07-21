package com.AlexNewg.youtube.logic;


import com.AlexNewg.youtube.console.ui.WrongNameException;
import com.AlexNewg.youtube.dao.GroupDao;
import com.AlexNewg.youtube.dao.GroupDaoFactory;
import com.AlexNewg.youtube.model.Group;

import java.util.List;

public class GroupLogic {

    private GroupDaoFactory groupDaoFactory = new GroupDaoFactory();
    private GroupDao service = (GroupDao) groupDaoFactory.createDao();

    public GroupLogic() {

    }

    public List<Group> getAllGroups() {
        return service.getAll();
    }

    public void createGroup(String name) throws WrongNameException {
        validateGroupName(name);
        service.create(new Group(name));
    }

    private void validateGroupName(String name) throws WrongNameException {
        if (!(name.length() < 20) && (name.length() > 0)) {
            throw new WrongNameException("wrong name: [" + name + "] it is too big");
        }
    }

    public void deleteGroup(String name) {
        service.delete(name);
    }

    public void updateGroup(String names) throws WrongNameException {
        String[] splited = names.split(" ");
        String oldName = splited[0];
        String newName = splited[1];
        validateGroupName(newName);
        service.update(oldName, newName);
    }
}
