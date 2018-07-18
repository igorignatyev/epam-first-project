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

public class OneCourseServlet extends HttpServlet {
    private static final CourseService courseService = new CourseServiceImpl();
    private static final TeacherService teacherService = new TeacherServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 0;

        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Course course = courseService.find(id);

        if (course == null) {
            ErrorHandler.error("Could not find a course", req, resp);
        }

        List<Teacher> teacherList = teacherService.findAll();

        if (teacherList == null) {
            ErrorHandler.error("Could not find any teachers", req, resp);
        }

        String name = course.getName();
        String description = course.getDescription();
        int teacherId = course.getTeacherId();

        req.setAttribute("id", id);
        req.setAttribute("name", name);
        req.setAttribute("description", description);
        req.setAttribute("teacherId", teacherId);
        req.setAttribute("teachers", teacherList);

        try {
            req.getRequestDispatcher("/one_course.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  {
        int id = 0;

        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        String action = req.getParameter("action");

        if ("editCourse".equals(action)) {
            try {
                String name = req.getParameter("name");
                String description = req.getParameter("description");
                int teacherId = Integer.parseInt(req.getParameter("teacherId"));
                courseService.update(id, new Course(id, name, description, teacherId)); //teacherId
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        if ("deleteCourse".equals(action)) {
            courseService.delete(id);
        }

        try {
            resp.sendRedirect("/courses");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
