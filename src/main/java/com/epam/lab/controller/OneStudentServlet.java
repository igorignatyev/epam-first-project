package com.epam.lab.controller;

import com.epam.lab.entity.Student;
import com.epam.lab.error.ErrorHandler;
import com.epam.lab.service.StudentService;
import com.epam.lab.service.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OneStudentServlet extends HttpServlet {
    private final StudentService studentService = new StudentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 0;

        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Student student = studentService.find(id);

        if (student == null) {
            ErrorHandler.error("Could not find a student", req, resp);
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

                studentService.update(id, new Student(firstName, lastName));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        if ("deleteStudent".equals(action)) {
            studentService.delete(id);
        }

        try {
            resp.sendRedirect("/students");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
