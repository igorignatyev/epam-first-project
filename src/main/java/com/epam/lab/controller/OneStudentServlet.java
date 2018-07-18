package com.epam.lab.controller;

import com.epam.lab.dao.GenericDao;
import com.epam.lab.dao.StudentDaoImpl;
import com.epam.lab.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OneStudentServlet extends HttpServlet {
    private final GenericDao<Student> studentDao = new StudentDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 0;

        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Student student = studentDao.find(id);

        if (student == null) {
            req.setAttribute("message", "Could not find a student");

            try {
                req.getRequestDispatcher("/error.jsp").forward(req, resp);
                return;
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }

        String firstName = student.getFirstName();
        String lastName = student.getLastName();

        req.setAttribute("id", id);
        req.setAttribute("firstName", firstName);
        req.setAttribute("lastName", lastName);

        try {
            req.getRequestDispatcher("/one_student.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 0;

        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        String action = req.getParameter("action");

        if ("editStudent".equals(action)) {
            try {
                String firstName = req.getParameter("firstName");
                String lastName = req.getParameter("lastName");

                studentDao.update(id, new Student(firstName, lastName));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        if ("deleteStudent".equals(action)) {
            studentDao.delete(id);
        }

        try {
            resp.sendRedirect("/students");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
