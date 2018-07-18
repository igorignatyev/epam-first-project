package com.epam.lab.controller;

import com.epam.lab.dao.*;
import com.epam.lab.entity.Course;
import com.epam.lab.entity.Participation;
import com.epam.lab.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class StudentServlet extends HttpServlet {
    private static final GenericDao<Student> studentDao = new StudentDaoImpl();
    private static final CourseDao courseDao = new CourseDaoImpl();
    private static final ParticipationDao participationDao = new ParticipationDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Course> availableCourses;

        int studentId = 0;

        try {
            studentId = Integer.parseInt(req.getParameter("studentId"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Student student = studentDao.find(studentId);

        List<Course> registeredCourses = courseDao.findAllRegistered(studentId);

        if (registeredCourses.isEmpty()) {
            availableCourses = courseDao.findAll();
        } else {
            availableCourses = courseDao.findAllAvailable(studentId);
        }

        List<Course> completedCourses = courseDao.findAllCompleted(studentId);

        availableCourses.removeAll(completedCourses);


        req.setAttribute("student", student);
        req.setAttribute("courses", availableCourses);
        req.setAttribute("registered_courses", registeredCourses);
        req.setAttribute("completed_courses", completedCourses);

        try {
            req.getRequestDispatcher("/student.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
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
            resp.sendRedirect("/student?studentId=" + studentId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
