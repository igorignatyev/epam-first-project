package com.epam.lab.dao;

import com.epam.lab.entity.Course;

import java.util.List;

public interface CourseDao extends GenericDao<Course>{
    @Override
    List<Course> findAll();

    @Override
    Course find(int id);

    @Override
    void create(Course obj);

    @Override
    void update(int id, Course obj);

    @Override
    void delete(int id);
}
