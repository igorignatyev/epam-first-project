package com.epam.lab.dao;

import com.epam.lab.entity.Teacher;

public interface TeacherDao extends GenericDao<Teacher> {
    String getHashedPasswordByEmail(String email);

    Teacher findByEmail(String email);
}