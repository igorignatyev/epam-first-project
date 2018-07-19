package com.epam.lab.controller;

import com.epam.lab.dao.*;
import com.epam.lab.entity.Course;
import com.epam.lab.entity.Teacher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class TeacherServlet extends HttpServlet {
    private static final TeacherDao teacherDao = new TeacherDaoImpl();
    private static final CourseDao courseDao = new CourseDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session != null) {
            String role = (String) session.getAttribute("role");
            String email = (String) session.getAttribute("email");

            if ("teacher".equals(role)) {
                int id = 0;

                try {
                    id = Integer.parseInt(req.getParameter("teacherId"));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                int checkableTeacherId = teacherDao.findByEmail(email).getId();

                if (checkableTeacherId == id) {
                    Teacher teacher = teacherDao.find(id);
                    req.setAttribute("teacher", teacher);

                    List<Course> teachersCoursesList = courseDao.findAllByTeacherId(id);
                    req.setAttribute("teachersCourses", teachersCoursesList);

                    req.getRequestDispatcher("/teacher.jsp?teacherId=" + id).forward(req, resp);
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

    private void accessDenied(HttpServletResponse resp) throws IOException {
        resp.sendRedirect("login.jsp");
    }
}