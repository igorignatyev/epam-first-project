package com.epam.lab.dao;

import com.epam.lab.entity.Teacher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;

public class TeacherDaoImplTest {
    private TeacherDao teacherDao;

    @Before
    public void setUp() {
        teacherDao = new TeacherDaoImpl();
    }

    @Test
    public void findAll() {
        List<Teacher> actualList = teacherDao.findAll();
        List<Teacher> expectedList = Collections.singletonList(new Teacher(1, "Roman", "Fyodorov"));
        Assert.assertThat(actualList, is(expectedList));
    }

    @Test
    public void find() {
        Teacher actualTeacher = teacherDao.find(1);
        Teacher expectedTeacher = new Teacher(1, "Roman", "Fyodorov");
        Assert.assertThat(actualTeacher, is(expectedTeacher));
    }

    @Test
    public void create() {
        Teacher addedTeacher = new Teacher(2, "Nikita", "Nikitin");
        teacherDao.create(addedTeacher);
        List<Teacher> actualList = teacherDao.findAll();
        List<Teacher> expectedList = Arrays.asList(new Teacher(1, "Roman", "Fyodorov"), addedTeacher);
        Assert.assertThat(actualList, is(expectedList));
    }

    @Test
    public void update() {
        Teacher actualTeacher = new Teacher(1, "Sergei", "Sergeev");
        teacherDao.update(1, actualTeacher);
        Teacher expectedTeacher = teacherDao.find(1);
        Assert.assertThat(actualTeacher, is(expectedTeacher));
    }

    @Test
    public void delete() {
        teacherDao.delete(1);
        List<Teacher> actualList = teacherDao.findAll();
        Assert.assertThat(actualList.isEmpty(), is(true));
    }
}