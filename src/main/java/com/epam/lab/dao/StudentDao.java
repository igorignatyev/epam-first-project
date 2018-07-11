package com.epam.lab.dao;

import com.epam.lab.entity.Student;

import java.util.List;

public interface StudentDao extends GenericDao<Student> {
    @Override
    List<Student> findAll();

    @Override
    Student find(int id);

    @Override
    void create(Student obj);

    @Override
    void update(Student obj);

    @Override
    void delete(Student obj);
}
