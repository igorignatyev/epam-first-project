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
    private static final ParticipationDao participationDao = new ParticipationDaoImpl();

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

                    List<Course> completedCourses = courseDao.findAllCompleted(id);

                    availableCourses.removeAll(completedCourses);

                    Student student = studentDao.find(id);

                    req.setAttribute("student", student);
                    req.setAttribute("courses", availableCourses);
                    req.setAttribute("registered_courses", registeredCourses);
                    req.setAttribute("completed_courses", completedCourses);

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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int studentId = 0;

        try {
            studentId = Integer.parseInt(req.getParameter("studentId"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        String action = req.getParameter("action");

        if ("deleteParticipation".equals(action)) {
            String[] options = req.getParameterValues("option");
            if (options != null) {
                for (String option : options) {
                    participationDao.delete(Integer.parseInt(option));
                }
            }
        }

        try {
            resp.sendRedirect("/student/student?studentId=" + studentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void accessDenied(HttpServletResponse resp) throws IOException {
        resp.sendRedirect("login.jsp");
    }
}
