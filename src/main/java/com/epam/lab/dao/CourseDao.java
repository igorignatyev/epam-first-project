package com.epam.lab.dao;

import com.epam.lab.entity.Course;

import java.util.List;

public interface CourseDao extends GenericDao<Course>{
    List<Course> findAvailable(int studentId);

    List<Course> findRegistered(int studentId);
}
