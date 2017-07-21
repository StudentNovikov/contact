package com.AlexNewg.youtube.dao;

public class GroupDaoFactory implements DaoFactory {
    @Override
    public IDao createDao() {
        return new GroupDao();
    }
}
