package com.epam.lab.dao;

import com.epam.lab.entity.Course;

import java.util.List;

public interface CourseDao extends GenericDao<Course>{
    List<Course> findAllAvailable(int studentId);

    List<Course> findAllRegistered(int studentId);

    List<Course> findAllCompleted(int studentId);

    List<Course> findAllByTeacherId(int id);
}
