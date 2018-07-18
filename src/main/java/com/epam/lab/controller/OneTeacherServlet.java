package com.epam.lab.controller;

import com.epam.lab.entity.Teacher;
import com.epam.lab.error.ErrorHandler;
import com.epam.lab.service.TeacherService;
import com.epam.lab.service.TeacherServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OneTeacherServlet extends HttpServlet {
    private static final TeacherService teacherService = new TeacherServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Teacher teacher = teacherService.find(id);

        if (teacher == null) {
            ErrorHandler.error("Could not find a teacher", req, resp);
        }

        String firstName = teacher.getFirstName();
        String lastName = teacher.getLastName();

        req.setAttribute("id", id);
        req.setAttribute("firstName", firstName);
        req.setAttribute("lastName", lastName);

        req.getRequestDispatcher("/one_teacher.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String action = req.getParameter("action");

        if ("editTeacher".equals(action)) {
            try {
                String firstName = req.getParameter("firstName");
                String lastName = req.getParameter("lastName");

                teacherService.update(id, new Teacher(id, firstName, lastName));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        if ("deleteTeacher".equals(action)) {
            teacherService.delete(id);
        }
        try {
            resp.sendRedirect("/teachers");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
