package com.epam.lab.controller;

import com.epam.lab.entity.Course;
import com.epam.lab.entity.Participation;
import com.epam.lab.entity.Student;
import com.epam.lab.entity.Teacher;
import com.epam.lab.error.ErrorHandler;
import com.epam.lab.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CourseServlet extends HttpServlet {
    private static final CourseService courseService = new CourseServiceImpl();
    private static final TeacherService teacherService = new TeacherServiceImpl();
    private static final ParticipationService participationService = new ParticipationServiceImpl();
    private static final StudentService studentService = new StudentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int studentId = 0;
        int courseId = 0;

        try {
            studentId = Integer.parseInt(req.getParameter("studentId"));
            courseId = Integer.parseInt(req.getParameter("courseId"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Student student = studentService.find(studentId);

        if (student == null) {
            ErrorHandler.error("Could not find a student", req, resp);
        }

        Course course = courseService.find(courseId);

        if (course == null) {
            ErrorHandler.error("Could not find a course", req, resp);
        }

        int teacherId = course.getTeacherId();
        Teacher teacher = teacherService.find(teacherId);

        if (teacher == null) {
            ErrorHandler.error("Could not find a teacher", req, resp);
        }

        req.setAttribute("student", student);
        req.setAttribute("course", course);
        req.setAttribute("teacher", teacher);

        try {
            req.getRequestDispatcher("/course.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int studentId = 0;
        int courseId = 0;
        int newId = 0;

        try {
            studentId = Integer.parseInt(req.getParameter("studentId"));
            courseId = Integer.parseInt(req.getParameter("courseId"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        String action = req.getParameter("action");

        if ("register".equals(action)) {
            List<Participation> participationList = participationService.findAll();
            if (participationList.isEmpty()) {
                newId = 1;
            } else {
                Participation lastParticipation = participationList.get(participationList.size() - 1);
                newId = lastParticipation.getId() + 1;
            }

            participationService.create(new Participation(newId, studentId, courseId));
        }

        try {
            resp.sendRedirect("/student?studentId=" + studentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
