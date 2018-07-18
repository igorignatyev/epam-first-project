package com.epam.lab.controller;

import com.epam.lab.entity.Course;
import com.epam.lab.entity.Review;
import com.epam.lab.error.ErrorHandler;
import com.epam.lab.service.CourseService;
import com.epam.lab.service.CourseServiceImpl;
import com.epam.lab.service.ReviewService;
import com.epam.lab.service.ReviewServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReviewServlet extends HttpServlet {
    private static final CourseService courseService = new CourseServiceImpl();
    private static final ReviewService reviewService = new ReviewServiceImpl();

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

        Review review = reviewService.findByStudentIdAndCourseId(studentId, courseId);

        if (review == null) {
            ErrorHandler.error("Could not find a review", req, resp);
        }

        req.setAttribute("review", review);

        Course course = courseService.find(courseId);

        if (course == null) {
            ErrorHandler.error("Could not find a course", req, resp);
        }

        req.setAttribute("course", course);

        try {
            req.getRequestDispatcher("/review.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
