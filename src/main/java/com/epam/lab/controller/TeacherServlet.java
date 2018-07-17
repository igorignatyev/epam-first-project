package com.epam.lab.controller;

import com.epam.lab.dao.CourseDao;
import com.epam.lab.dao.CourseDaoImpl;
import com.epam.lab.dao.GenericDao;
import com.epam.lab.dao.TeacherDaoImpl;
import com.epam.lab.entity.Course;
import com.epam.lab.entity.Teacher;
import com.epam.lab.error.ErrorHandler;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
public class TeacherServlet extends HttpServlet {

    private static final GenericDao<Teacher> teacherDao = new TeacherDaoImpl();
    private static final CourseDao courseDao = new CourseDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 0;

        try {
            id = Integer.parseInt(req.getParameter("teacherId"));
        } catch (NumberFormatException e) {
            log.error(e.getMessage());
        }

        Teacher teacher = teacherDao.find(id);

        if (teacher == null) {
            ErrorHandler.error("Could not find a teacher", req, resp);
        }

        req.setAttribute("teacher", teacher);

        List<Course> teachersCoursesList = courseDao.findAllByTeacherId(id);
        req.setAttribute("teachersCourses", teachersCoursesList);

        try {
            req.getRequestDispatcher("/teacher.jsp?teacherId=" + id).forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}