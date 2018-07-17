package com.epam.lab.controller;

import com.epam.lab.dao.GenericDao;
import com.epam.lab.dao.StudentDaoImpl;
import com.epam.lab.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllStudentsServlet extends HttpServlet {
    private GenericDao<Student> studentDao = new StudentDaoImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> studentList = studentDao.findAll();

        req.setAttribute("students", studentList);

        req.getRequestDispatcher("/students.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("addStudent".equals(action)) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                String firstName = req.getParameter("firstName");
                String lastName = req.getParameter("lastName");

                studentDao.create(new Student(id, firstName, lastName));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if ("deleteStudent".equals(action)) {
            String[] options = req.getParameterValues("option");
            for (String option: options) {
                studentDao.delete(Integer.parseInt(option));
            }
        }

        resp.sendRedirect("/students");
    }
}
