package com.AlexNewg.youtube.dao;


import com.AlexNewg.youtube.model.Group;

import java.util.List;

/**
 * Created by Alex on 12.07.2017.
 */
public class GroupService {

    private final MemoryStorage storage;

    public GroupService(MemoryStorage memoryStorage) {
        this.storage = memoryStorage;
    }

    public List<Group> getAllGroups() {
        return storage.getAllGroups();
    }

    public void updateGroup(Group groupOld, Group groupNew) {
        storage.updateGroup(groupOld, groupNew);
    }

    public int createGroup(Group group) {
        return storage.createGroup(group);
    }

    public void deleteGroup(Group group) {
        storage.deleteGroup(group);

    }

}
