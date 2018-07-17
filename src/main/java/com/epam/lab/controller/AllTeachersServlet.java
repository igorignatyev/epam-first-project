package com.epam.lab.controller;

import com.epam.lab.dao.GenericDao;
import com.epam.lab.dao.TeacherDaoImpl;
import com.epam.lab.entity.Teacher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllTeachersServlet extends HttpServlet {
    private static final GenericDao<Teacher> teacherDao = new TeacherDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Teacher> teacherList = teacherDao.findAll();
        req.setAttribute("teachers", teacherList);
        req.getRequestDispatcher("/teachers.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        if ("addTeacher".equals(action)) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                String firstName = req.getParameter("firstName");
                String lastName = req.getParameter("lastName");
                teacherDao.create(new Teacher(id, firstName, lastName));

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if ("deleteTeacher".equals(action)) {
            String[] options = req.getParameterValues("option");
            for (String option: options) {
                teacherDao.delete(Integer.parseInt(option));
            }
        }

        resp.sendRedirect("/teachers");
    }
}


