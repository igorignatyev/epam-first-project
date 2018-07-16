package com.epam.lab.dao;

import com.epam.lab.entity.Teacher;

import java.util.List;

public interface TeacherDao extends GenericDao<Teacher> {
    @Override
    List<Teacher> findAll();

    @Override
    Teacher find(int id);

    @Override
    void create(Teacher teacher);

    @Override
    void update(int id, Teacher teacher);

    @Override
    void delete(int id);

    public Teacher findByEmail(String email);

    public boolean verifyPassword(String email, String password);
}