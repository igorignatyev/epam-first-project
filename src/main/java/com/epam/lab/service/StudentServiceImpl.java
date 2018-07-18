package com.epam.lab.service;

import com.epam.lab.dao.StudentDao;
import com.epam.lab.dao.StudentDaoImpl;
import com.epam.lab.entity.Student;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private static final StudentDao studentDao = new StudentDaoImpl();

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public Student find(int id) {
        return studentDao.find(id);
    }

    @Override
    public void create(Student student) {
        studentDao.create(student);
    }

    @Override
    public void update(int id, Student student) {
        studentDao.update(id, student);
    }

    @Override
    public void delete(int id) {
        studentDao.delete(id);
    }

    @Override
    public List<Student> findAllByCourseId(int courseId) {
        return studentDao.findAllByCourseId(courseId);
    }
}
