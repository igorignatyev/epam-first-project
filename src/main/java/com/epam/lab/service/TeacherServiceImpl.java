package com.epam.lab.service;

import com.epam.lab.dao.TeacherDao;
import com.epam.lab.dao.TeacherDaoImpl;
import com.epam.lab.entity.Teacher;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {
    private static final TeacherDao teacherDao = new TeacherDaoImpl();

    @Override
    public List<Teacher> findAll() {
        return teacherDao.findAll();
    }

    @Override
    public Teacher find(int id) {
        return teacherDao.find(id);
    }

    @Override
    public void create(Teacher teacher) {
        teacherDao.create(teacher);
    }

    @Override
    public void update(int id, Teacher teacher) {
        teacherDao.update(id, teacher);
    }

    @Override
    public void delete(int id) {
        teacherDao.delete(id);
    }
}
