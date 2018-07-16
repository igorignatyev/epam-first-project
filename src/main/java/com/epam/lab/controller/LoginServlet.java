package com.epam.lab.controller;

import com.epam.lab.dao.StudentDao;
import com.epam.lab.dao.StudentDaoImpl;
import com.epam.lab.dao.TeacherDao;
import com.epam.lab.dao.TeacherDaoImpl;
import com.epam.lab.entity.Student;
import com.epam.lab.entity.Teacher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    TeacherDao teacherDao = new TeacherDaoImpl();
    StudentDao studentDao = new StudentDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("login".equals(action)) {
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            if (teacherDao.verifyPassword(email, password)) {
                Teacher teacher = teacherDao.findByEmail(email);
            } else if (studentDao.verifyPassword(email, password)) {
                Student student = studentDao.findByEmail(email);
            }
        }
    }

}
