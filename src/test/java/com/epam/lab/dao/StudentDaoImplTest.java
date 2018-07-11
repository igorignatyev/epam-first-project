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
        List<Student> studentList = studentDao.findAll();

        List<Student> testStudentList = Collections.singletonList(new Student(1, "Andrew", "Ivanov"));

        Assert.assertThat(studentList, is(testStudentList));
    }

}