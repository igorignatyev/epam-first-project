package com.epam.lab.dao;

import com.epam.lab.entity.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TeacherDaoImplTest {
    private TeacherDao teacherDao;

    @BeforeEach
    public void setUp() {
        teacherDao = new TeacherDaoImpl();
    }

    @Test
    public void findAll() {
        List<Teacher> actualList = teacherDao.findAll();
        List<Teacher> expectedList = Arrays.asList(new Teacher(1, "Roman", "Fyodorov"), new Teacher(2, "Alex", "White"));
        assertEquals(actualList, expectedList);
    }

    @Test
    public void find() {
        Teacher actualTeacher = teacherDao.find(1);
        Teacher expectedTeacher = new Teacher(1, "Roman", "Fyodorov");
        assertEquals(actualTeacher, expectedTeacher);
    }

    @Test
    public void create() {
        Teacher addedTeacher = new Teacher(3, "Nikita", "Nikitin");
        teacherDao.create(addedTeacher);
        List<Teacher> actualList = teacherDao.findAll();
        List<Teacher> expectedList = Arrays.asList(new Teacher(1, "Roman", "Fyodorov"), new Teacher(2, "Alex", "White"), addedTeacher);
        assertEquals(actualList, expectedList);
    }

    @Test
    public void update() {
        Teacher actualTeacher = new Teacher(1, "Sergei", "Sergeev");
        teacherDao.update(1, actualTeacher);
        Teacher expectedTeacher = teacherDao.find(1);
        assertEquals(actualTeacher, expectedTeacher);
    }

    @Test
    public void delete() {
        teacherDao.delete(1);
        List<Teacher> actualList = teacherDao.findAll();
        assertEquals(1, actualList.size());
    }
}