package com.epam.lab.service;

import com.epam.lab.dao.CourseDao;
import com.epam.lab.dao.CourseDaoImpl;
import com.epam.lab.entity.Course;

import java.util.List;

public class CourseServiceImpl implements CourseService {
    private static final CourseDao courseDao = new CourseDaoImpl();

    @Override
    public List<Course> findAll() {
        return courseDao.findAll();
    }

    @Override
    public Course find(int id) {
        return courseDao.find(id);
    }

    @Override
    public void create(Course course) {
        courseDao.create(course);
    }

    @Override
    public void update(int id, Course course) {
        courseDao.update(id, course);
    }

    @Override
    public void delete(int id) {
        courseDao.delete(id);
    }

    @Override
    public List<Course> findAllAvailable(int studentId) {
        return courseDao.findAllAvailable(studentId);
    }

    @Override
    public List<Course> findAllRegistered(int studentId) {
        return courseDao.findAllRegistered(studentId);
    }

    @Override
    public List<Course> findAllCompleted(int studentId) {
        return courseDao.findAllCompleted(studentId);
    }

    @Override
    public List<Course> findAllByTeacherId(int id) {
        return courseDao.findAllByTeacherId(id);
    }
}
