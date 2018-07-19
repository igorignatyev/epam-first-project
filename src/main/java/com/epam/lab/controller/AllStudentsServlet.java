package com.epam.lab.controller;

import com.epam.lab.dao.GenericDao;
import com.epam.lab.dao.StudentDaoImpl;
import com.epam.lab.entity.Student;
import com.epam.lab.error.ErrorHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.epam.lab.controller.auth.RegistrationServlet.hashPassword;

public class AllStudentsServlet extends HttpServlet {
    private GenericDao<Student> studentDao = new StudentDaoImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> studentList = studentDao.findAll();

        if (studentList == null) {
            ErrorHandler.error("Could not find any students", req, resp);
        }

        req.setAttribute("students", studentList);

        req.getRequestDispatcher("/students.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("addStudent".equals(action)) {
            try {
                int newId = getNewId();
                String firstName = req.getParameter("firstName");
                String lastName = req.getParameter("lastName");
                String login = req.getParameter("login");
                String password = req.getParameter("password");

                studentDao.create(new Student(newId, firstName, lastName, login, hashPassword(password)));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if ("deleteStudent".equals(action)) {
            String[] options = req.getParameterValues("option");
            for (String option : options) {
                studentDao.delete(Integer.parseInt(option));
            }
        }

        resp.sendRedirect("/admin/students");
    }

    private int getNewId() {
        int newId;
        List<Student> studentList = studentDao.findAll();

        if (studentList.isEmpty()) {
            newId = 1;
        } else {
            Student lastStudent = studentList.get(studentList.size() - 1);
            newId = lastStudent.getId() + 1;
        }

        return newId;
    }
}
