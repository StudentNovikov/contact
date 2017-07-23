package com.AlexNewg.youtube.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Contact implements Serializable{

    private int Id;
    private String name;
    private String description;
    private List<Group> groups;

    Contact(String name, String description) {
        setName(name);
        setDescription(description);
        groups = new ArrayList<>();
    }

    public void setId(int id) {
        this.Id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void addGroup(Group group) {
        this.groups.add(group);
    }

    public List<Group> getGroups() {
        return groups;
    }


    public String toStringDetailed() {
        String contactGroupName = "";
        if (groups != null) {
            for (Group s : groups) {
                contactGroupName = contactGroupName + " " + s.getName();
            }
        }
        return ("Contact id : " + getId() + " , name : " + getName() + " , description : " + getDescription() +
                " , groups : " + contactGroupName);

    }

    @Override
    public String toString() {
        String contactGroupName = "";
        if (groups != null) {
            for (Group s : groups) {
                contactGroupName = contactGroupName + " " + s.getName();
            }
        }
        return ("Contact id : " + getId() + " , name : " + getName() +
                " , groups : " + contactGroupName);

    }
}
