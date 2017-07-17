package com.AlexNewg.youtube.model;

import java.io.Serializable;

/**
 * Created by Alex on 11.07.2017.
 */
public class Group implements Serializable {
    private int Id;
    private String name;

    public Group(String name) {
        setName(name);
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
