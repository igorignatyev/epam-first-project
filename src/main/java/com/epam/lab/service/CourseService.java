package com.epam.lab.service;

import com.epam.lab.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> findAll();

    Course find(int id);

    void create(Course course);

    void update(int id, Course course);

    void delete(int id);

    List<Course> findAllAvailable(int studentId);

    List<Course> findAllRegistered(int studentId);

    List<Course> findAllCompleted(int studentId);

    List<Course> findAllByTeacherId(int id);
}
