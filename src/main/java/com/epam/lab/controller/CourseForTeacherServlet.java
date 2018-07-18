package com.epam.lab.controller;

import com.epam.lab.entity.Course;
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

public class CourseForTeacherServlet extends HttpServlet {
    private static final CourseService courseService = new CourseServiceImpl();
    private static final StudentService studentService = new StudentServiceImpl();
    private static final TeacherService teacherService = new TeacherServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int teacherId = 0;
        int courseId = 0;

        try {
            teacherId = Integer.parseInt(req.getParameter("teacherId"));
            courseId = Integer.parseInt(req.getParameter("courseId"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Course course = courseService.find(courseId);

        if (course == null) {
            ErrorHandler.error("Could not find a course", req, resp);
        }

        req.setAttribute("course", course);

        List<Student> studentsInThisCourseList = studentService.findAllByCourseId(courseId);

        if (studentsInThisCourseList == null) {
            ErrorHandler.error("Could not find any students for this course", req, resp);
        }

        req.setAttribute("students", studentsInThisCourseList);

        Teacher teacher = teacherService.find(teacherId);

        if (teacher == null) {
            ErrorHandler.error("Could not find a teacher", req, resp);
        }

        req.setAttribute("teacher", teacher);

        try {
            req.getRequestDispatcher("course_for_teacher.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}