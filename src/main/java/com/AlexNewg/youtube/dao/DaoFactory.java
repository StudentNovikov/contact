package com.AlexNewg.youtube.dao;

public class DaoFactory {

    public IDao createContactDao() {
        return new ContactDao();
    }

    public IDao createGroupDao() {
        return new GroupDao();
    }
}
