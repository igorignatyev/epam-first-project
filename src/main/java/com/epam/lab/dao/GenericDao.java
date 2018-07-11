package com.epam.lab.dao;

import com.epam.lab.config.DatabaseConfig;

import java.sql.Connection;
import java.util.List;

public interface GenericDao<T> {
    Connection connection = DatabaseConfig.getDBConnection();


    List<T> findAll();

    T find(int id);

    void create(T obj);

    void update(int id, T obj);

    void delete(int id);
}

