package com.epam.lab.dao;

import com.epam.lab.entity.Student;

import java.util.List;

public interface StudentDao extends GenericDao<Student> {
    List<Student> findAllByCourseId(int courseId);
}