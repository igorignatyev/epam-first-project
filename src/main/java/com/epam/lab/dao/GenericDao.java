package com.epam.lab.dao;

import java.util.List;

public interface GenericDao<T> {
    List<T> findAll();
    T find(int id);
    void create(T obj);
    void update(T obj);
    void delete(T obj);
}