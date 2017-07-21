package com.AlexNewg.youtube.dao;

public class ContactDaoFactory implements DaoFactory {
    @Override
    public IDao createDao() {
        return new ContactDao();
    }
}
