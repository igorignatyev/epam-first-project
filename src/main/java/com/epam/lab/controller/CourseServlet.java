package com.epam.lab.controller;

import com.epam.lab.dao.*;
import com.epam.lab.entity.Course;
import com.epam.lab.entity.Participation;
import com.epam.lab.entity.Student;
import com.epam.lab.entity.Teacher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CourseServlet extends HttpServlet {
    private static final GenericDao<Course> courseDao = new CourseDaoImpl();
    private static final GenericDao<Teacher> teacherDao = new TeacherDaoImpl();
    private static final GenericDao<Participation> participationDao = new ParticipationDaoImpl();
    private static final GenericDao<Student> studentDao = new StudentDaoImpl();

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

        Student student = studentDao.find(studentId);
        Course course = courseDao.find(courseId);

        int teacherId = course.getTeacherId();
        Teacher teacher = teacherDao.find(teacherId);

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
            List<Participation> participationList = participationDao.findAll();
            if (participationList.isEmpty()) {
                newId = 1;
            } else {
                Participation lastParticipation = participationList.get(participationList.size() - 1);
                newId = lastParticipation.getId() + 1;
            }

            participationDao.create(new Participation(newId, studentId, courseId));
        }

        try {
            resp.sendRedirect("/student?studentId=" + studentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
