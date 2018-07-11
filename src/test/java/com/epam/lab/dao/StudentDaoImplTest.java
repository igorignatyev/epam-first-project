package com.epam.lab.dao;

import com.epam.lab.entity.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StudentDaoImplTest {
    private StudentDao studentDao;

    @Before
    public void setUp() throws Exception {
        studentDao = new StudentDaoImpl();
    }

    @Test
    public void findAll() throws Exception {
        List<Student> actualList = studentDao.findAll();

        List<Student> expectedList = Collections.singletonList(new Student(1, "Andrew", "Ivanov"));

        Assert.assertThat(actualList, is(expectedList));
    }

    @Test
    public void find() {
        Student actualStudent = studentDao.find(1);

        Student expectedStudent = new Student(1, "Andrew", "Ivanov");

        Assert.assertThat(actualStudent, is(expectedStudent));
    }

}