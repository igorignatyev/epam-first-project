package com.epam.lab.service;

import com.epam.lab.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();

    Student find(int id);

    void create(Student student);

    void update(int id, Student student);

    void delete(int id);

    List<Student> findAllByCourseId(int courseId);
}
