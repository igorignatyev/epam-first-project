package com.epam.lab.dao;

import com.epam.lab.entity.Course;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

class CourseDaoImplTest {

    private GenericDao<Course> courseDao = new CourseDaoImpl();

    @Test
    void findAll() throws Exception{
        List<Course> courseList = courseDao.findAll();

        List<Course> expectedList = Arrays.asList(new Course(1, "Java", "This is Java course", 1), new Course(2, "C", "This is C course", 2));

        assertEquals(courseList, expectedList);
    }

    @Test
    void find() {
        Course course = courseDao.find(1);

        Course expectedCourse =new Course(1, "Java", "This is Java course", 1);

        assertEquals(course, expectedCourse);
    }

    @Test
    void create() {

        Course course = new Course(3, "C", "C course", 3);
        courseDao.create(course);

        List<Course> courseList = courseDao.findAll();

        List<Course> expectedList = Arrays.asList(new Course(1, "Java", "This is Java course", 1), new Course(2, "C", "This is C course", 2) , new Course(3, "C", "C course", 3));

        assertEquals(courseList, expectedList);
    }

    @Test
    void update() {
        courseDao.update(2, new Course(2, "Python", "Python course", 2));

        List<Course> courseList = courseDao.findAll();

        List<Course> expectedList = Arrays.asList(new Course(1, "Java", "This is Java course", 1),  new Course(2, "Python", "Python course", 2));

        assertEquals(courseList, expectedList);
    }

    @Test
    void delete() {
        courseDao.delete(1);

        List<Course> courseList = courseDao.findAll();

        assertEquals(courseList.size() == 1, true);
    }
}