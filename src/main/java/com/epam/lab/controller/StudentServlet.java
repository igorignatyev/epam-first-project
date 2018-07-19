package com.epam.lab.controller;

import com.epam.lab.dao.*;
import com.epam.lab.entity.Course;
import com.epam.lab.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class StudentServlet extends HttpServlet {
    private static final StudentDao studentDao = new StudentDaoImpl();
    private static final CourseDao courseDao = new CourseDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session != null) {
            String role = (String) session.getAttribute("role");
            String email = (String) session.getAttribute("email");

            if ("student".equals(role)) {
                int id = 0;

                try {
                    id = Integer.parseInt(req.getParameter("studentId"));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                int checkableStudentId = studentDao.findByEmail(email).getId();

                if (checkableStudentId == id) {

                    List<Course> availableCourses;

                    List<Course> registeredCourses = courseDao.findAllRegistered(id);

                    if (registeredCourses.isEmpty()) {
                        availableCourses = courseDao.findAll();
                    } else {
                        availableCourses = courseDao.findAllAvailable(id);
                    }

                    Student student = studentDao.find(id);

                    req.setAttribute("student", student);
                    req.setAttribute("courses", availableCourses);
                    req.setAttribute("registered_courses", registeredCourses);

                    try {
                        req.getRequestDispatcher("/student.jsp").forward(req, resp);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    accessDenied(resp);
                }
            } else {
                accessDenied(resp);
            }
        } else {
            accessDenied(resp);
        }
    }

    private void accessDenied(HttpServletResponse resp) throws IOException {
        resp.sendRedirect("login.jsp");
    }
}
