package com.AlexNewg.youtube.dao;

import java.util.List;

interface IDao<T> {
     List<T> getAll();
    void create(T t);
    void delete(String name);
    void update(String oldName, String newName);
}
