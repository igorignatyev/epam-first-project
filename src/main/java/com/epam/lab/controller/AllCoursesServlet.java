package com.epam.lab.controller;

import com.epam.lab.dao.CourseDaoImpl;
import com.epam.lab.dao.GenericDao;
import com.epam.lab.dao.TeacherDaoImpl;
import com.epam.lab.entity.Course;
import com.epam.lab.entity.Teacher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllCoursesServlet extends HttpServlet {
    private static final GenericDao<Course> courseDao = new CourseDaoImpl();
    private static final GenericDao<Teacher> teacherDao = new TeacherDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Course> courseList = courseDao.findAll();
        List<Teacher> teacherList = teacherDao.findAll();

        req.setAttribute("courses", courseList);
        req.setAttribute("teachers", teacherList);

        req.getRequestDispatcher("/courses.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("addCourse".equals(action)) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                String name = req.getParameter("name");
                String description = req.getParameter("description");
                int teacherId = Integer.parseInt(req.getParameter("teacherId"));

                courseDao.create(new Course(id, name, description, teacherId));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if ("deleteCourse".equals(action)) {
            String[] options = req.getParameterValues("option");
            for (String option: options) {
                courseDao.delete(Integer.parseInt(option));
            }
        }

        resp.sendRedirect("/courses");
    }
}
