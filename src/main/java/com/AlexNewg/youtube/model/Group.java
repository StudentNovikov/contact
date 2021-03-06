package com.AlexNewg.youtube.model;

import java.io.Serializable;

public class Group implements Serializable {
    private int Id;
    private String name;

    public Group(String name) {
        setName(name);
    }

    public Group(int id , String name) {
        setName(name);
        setId(id);
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ("Group Id : " + getId() + " , name : " + getName());
    }

}
