package com.epam.lab.dao;

import com.epam.lab.entity.Student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StudentDaoImplTest {
    private GenericDao<Student> studentDao;

    @BeforeEach
    public void setUp() throws Exception {
        studentDao = new StudentDaoImpl();
    }

    @Test
    public void findAll() throws Exception {
        List<Student> actualList = studentDao.findAll();

        List<Student> expectedList = Arrays.asList(new Student(1, "Andrew", "Ivanov"), new Student(2, "Ivan", "Markov"));

        assertEquals(actualList, expectedList);
    }

    @Test
    public void find() {
        Student actualStudent = studentDao.find(1);

        Student expectedStudent = new Student(1, "Andrew", "Ivanov");

        assertEquals(actualStudent, expectedStudent);
    }

    @Test
    public void create() {
        Student student = new Student(3, "Bob", "Black");
        studentDao.create(student);

        List<Student> actualList = studentDao.findAll();
        List<Student> expectedList = Arrays.asList(new Student(1, "Andrew", "Ivanov"), new Student(2, "Ivan", "Markov"),
                new Student(3, "Bob", "Black"));

        assertEquals(actualList, expectedList);
    }

    @Test
    public void update() {
        studentDao.update(2, new Student("Max", "Ivanov"));

        List<Student> actualList = studentDao.findAll();

        List<Student> expectedList = Arrays.asList(new Student(1, "Andrew", "Ivanov"), new Student(2, "Max", "Ivanov"));

        assertEquals(actualList, expectedList);
    }

    @Test
    public void delete() {
        studentDao.delete(1);

        List<Student> actualList2 = studentDao.findAll();

        System.out.println("DELETE AFTER" + actualList2);

        assertEquals(actualList2.size() == 1, true);
    }
}