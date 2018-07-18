package com.epam.lab.service;

import com.epam.lab.entity.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> findAll();

    Teacher find(int id);

    void create(Teacher teacher);

    void update(int id, Teacher teacher);

    void delete(int id);
}
