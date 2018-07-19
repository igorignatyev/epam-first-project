package com.epam.lab.controller;

import com.epam.lab.dao.*;
import com.epam.lab.entity.Participation;
import com.epam.lab.entity.Review;
import com.epam.lab.entity.Student;
import com.epam.lab.entity.Teacher;
import com.epam.lab.error.ErrorHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class StudentForTeacherServlet extends HttpServlet {
    private static final GenericDao<Student> studentDao = new StudentDaoImpl();
    private static final GenericDao<Review> reviewDao = new ReviewDaoImpl();
    private static final GenericDao<Teacher> teacherDao = new TeacherDaoImpl();
    private static final ParticipationDao participationDao = new ParticipationDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int studentId = 0;
        int teacherId = 0;
        int courseId = 0;

        try {
            teacherId = Integer.parseInt(req.getParameter("teacherId"));
            studentId = Integer.parseInt(req.getParameter("studentId"));
            courseId = Integer.parseInt(req.getParameter("courseId"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        req.setAttribute("studentId", studentId);
        req.setAttribute("courseId", courseId);

        Student student = studentDao.find(studentId);

        if (student == null) {
            ErrorHandler.error("Could not find a student", req, resp);
        }

        req.setAttribute("student", student);

        Teacher teacher = teacherDao.find(teacherId);

        if (teacher == null) {
            ErrorHandler.error("Could not find a teacher", req, resp);
        }

        req.setAttribute("teacher", teacher);

        try {
            req.getRequestDispatcher("/student_for_teacher.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int newId;
        int studentId = 0;
        int courseId = 0;
        int teacherId = 0;

        try {
            studentId = Integer.parseInt(req.getParameter("studentId"));
            courseId = Integer.parseInt(req.getParameter("courseId"));
            teacherId = Integer.parseInt(req.getParameter("teacherId"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        String action = req.getParameter("action");

        if ("send".equals(action)) {
            newId = getNewId();
            String feedback = req.getParameter("feedback");
            Participation participation = participationDao.findByStudentIdAndCourseId(studentId, courseId);
            int participationId = participation.getId();
            int mark = Integer.parseInt(req.getParameter("mark"));

            reviewDao.create(new Review(newId, feedback, mark, participationId));
        }

        try {
            resp.sendRedirect("/teacher/course_for_teacher?teacherId=" + teacherId + "&" + "courseId=" + courseId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getNewId() {
        int newId;
        List<Review> reviewList = reviewDao.findAll();

        if (reviewList.isEmpty()) {
            newId = 1;
        } else {
            Review lastReview = reviewList.get(reviewList.size() - 1);
            newId = lastReview.getId() + 1;
        }

        return newId;
    }
}