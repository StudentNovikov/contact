package com.AlexNewg.youtube.dao;


import com.AlexNewg.youtube.model.Group;

import java.util.List;

public class GroupDao implements IDao<Group> {

    private final MemoryStorage storage = MemoryStorage.getInstance();
    private List<Group> allGroups = storage.getAllGroups();

    public List<Group> getAll() {
        return allGroups;
    }

    public void update(String oldName, String newName) {

        for (Group allGroup : allGroups) {
            if (allGroup.getName().equals(oldName)) {
                allGroup.setName(newName);
            }
        }
    }

    public void create(Group group) {

        group.setId(getMaxGroupId());
        allGroups.add(group);

    }

    private int getMaxGroupId() {
        if (allGroups.isEmpty()) {
            return 1;
        }
        return allGroups.get(allGroups.size() - 1).getId() + 1;
    }


    public void delete(String name) {

        for (int i = 0; i < allGroups.size(); i++) {
            if (allGroups.get(i).getName().equals(name)) {
                allGroups.remove(i--);
            }
        }

    }

}
