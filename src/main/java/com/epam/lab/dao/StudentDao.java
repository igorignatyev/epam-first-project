package com.epam.lab.dao;

import com.epam.lab.entity.Student;

import java.util.List;

public interface StudentDao extends GenericDao<Student> {
    @Override
    List<Student> findAll();

    @Override
    Student find(int id);

    @Override
    void create(Student student);

    @Override
    void update(int id, Student student);

    @Override
    void delete(int id);

    public Student findByEmail(String email);

    public boolean verifyPassword(String email, String password);
}
