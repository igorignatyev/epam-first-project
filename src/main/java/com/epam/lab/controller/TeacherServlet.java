package com.epam.lab.controller;

import com.epam.lab.entity.Course;
import com.epam.lab.entity.Teacher;
import com.epam.lab.error.ErrorHandler;
import com.epam.lab.service.CourseService;
import com.epam.lab.service.CourseServiceImpl;
import com.epam.lab.service.TeacherService;
import com.epam.lab.service.TeacherServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TeacherServlet extends HttpServlet {
    private static final TeacherService teacherService = new TeacherServiceImpl();
    private static final CourseService courseService = new CourseServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 0;

        try {
            id = Integer.parseInt(req.getParameter("teacherId"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Teacher teacher = teacherService.find(id);

        if (teacher == null) {
            ErrorHandler.error("Could not find a teacher", req, resp);
        }

        req.setAttribute("teacher", teacher);

        List<Course> teachersCoursesList = courseService.findAllByTeacherId(id);
        req.setAttribute("teachersCourses", teachersCoursesList);

        try {
            req.getRequestDispatcher("/teacher.jsp?teacherId=" + id).forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}