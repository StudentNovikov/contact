package com.AlexNewg.youtube.dao;


import com.AlexNewg.youtube.model.Group;

import java.util.List;

public class GroupDao implements IDao<Group> {

    private final MemoryStorage storage = MemoryStorage.getInstance();

    public List<Group> getAll() {
        return storage.getAllGroups();
    }

    public void update(String oldName, String newName) {
        storage.updateGroup(oldName, newName);
    }

    public void create(Group group) {
        storage.createGroup(group);
    }

    public void delete(String name) {
        storage.deleteGroup(name);

    }

}
