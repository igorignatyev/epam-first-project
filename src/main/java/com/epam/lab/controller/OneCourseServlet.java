package com.epam.lab.controller;

import com.epam.lab.dao.CourseDaoImpl;
import com.epam.lab.dao.GenericDao;
import com.epam.lab.dao.TeacherDaoImpl;
import com.epam.lab.entity.Course;
import com.epam.lab.entity.Teacher;
import com.epam.lab.error.ErrorHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OneCourseServlet extends HttpServlet {
    private static final GenericDao<Course> courseDao = new CourseDaoImpl();
    private static final GenericDao<Teacher> teacherDao = new TeacherDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 0;

        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Course course = courseDao.find(id);

        if (course == null) {
            ErrorHandler.error("Could not find a course", req, resp);
        }

        List<Teacher> teacherList = teacherDao.findAll();

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
                courseDao.update(id, new Course(id, name, description, teacherId)); //teacherId
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        if ("deleteCourse".equals(action)) {
            courseDao.delete(id);
        }

        try {
            resp.sendRedirect("/admin/courses");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
