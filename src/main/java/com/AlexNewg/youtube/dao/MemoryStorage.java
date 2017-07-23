package com.AlexNewg.youtube.dao;

import com.AlexNewg.youtube.model.Contact;
import com.AlexNewg.youtube.model.Group;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MemoryStorage {

    private static MemoryStorage instance;
    private List<Contact> allContacts = new ArrayList<>();
    private List<Group> allGroups = new ArrayList<>();

    private MemoryStorage() {
        initStartingData();
    }

    public static MemoryStorage getInstance() {
        if (instance == null) {
            instance = new MemoryStorage();
        }
        return instance;
    }

    private void initStartingData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("storage.dat"))) {
            allContacts = (List<Contact>) ois.readObject();
            allGroups = (List<Group>) ois.readObject();
        } catch (Exception ex) {
            System.out.println("Exception while loading file: " + ex.getMessage());
        }
    }

    List<Contact> getAllContacts() {
        return allContacts;
    }

    List<Group> getAllGroups() {
        return allGroups;
    }

    public void saveToStorage() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("storage.dat"))) {
            oos.writeObject(allContacts);
            oos.writeObject(allGroups);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
