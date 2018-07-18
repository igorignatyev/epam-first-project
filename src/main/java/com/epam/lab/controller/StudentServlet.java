package com.epam.lab.controller;

import com.epam.lab.entity.Course;
import com.epam.lab.entity.Student;
import com.epam.lab.error.ErrorHandler;
import com.epam.lab.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class StudentServlet extends HttpServlet {
    private static final StudentService studentService = new StudentServiceImpl();
    private static final CourseService courseService = new CourseServiceImpl();
    private static final ParticipationService participationService = new ParticipationServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Course> availableCourses;

        int studentId = 0;

        try {
            studentId = Integer.parseInt(req.getParameter("studentId"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Student student = studentService.find(studentId);

        if (student == null) {
            ErrorHandler.error("Could not find a student", req, resp);
            return;
        }

        List<Course> registeredCourses = courseService.findAllRegistered(studentId);

        if (registeredCourses.isEmpty()) {
            availableCourses = courseService.findAll();
        } else {
            availableCourses = courseService.findAllAvailable(studentId);
        }

        List<Course> completedCourses = courseService.findAllCompleted(studentId);

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
                    participationService.delete(Integer.parseInt(option));
                }
            }
        }

        try {
            resp.sendRedirect("/student?studentId=" + studentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
