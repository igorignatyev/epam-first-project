package com.epam.lab.controller;

import com.epam.lab.dao.GenericDao;
import com.epam.lab.entity.Teacher;
import com.epam.lab.dao.TeacherDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OneTeacherServlet extends HttpServlet {
    private static final GenericDao<Teacher> teacherDao = new TeacherDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Teacher teacher = teacherDao.find(id);

        if (teacher == null) {
            req.setAttribute("message", "Could not find a teacher");

            try {
                req.getRequestDispatcher("/error.jsp").forward(req, resp);
                return;
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }

        String firstName = teacher.getFirstName();
        String lastName = teacher.getLastName();

        req.setAttribute("id", id);
        req.setAttribute("firstName", firstName);
        req.setAttribute("lastName", lastName);

        req.getRequestDispatcher("/one_teacher.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String action = req.getParameter("action");

        if ("editTeacher".equals(action)) {
            try {
                String firstName = req.getParameter("firstName");
                String lastName = req.getParameter("lastName");

                teacherDao.update(id, new Teacher(id, firstName, lastName));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        if ("deleteTeacher".equals(action)) {
            teacherDao.delete(id);
        }
        try {
            resp.sendRedirect("/teachers");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
