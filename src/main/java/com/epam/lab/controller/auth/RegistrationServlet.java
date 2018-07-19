package com.epam.lab.controller.auth;

import com.epam.lab.dao.*;
import com.epam.lab.entity.Teacher;
import com.epam.lab.entity.Student;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationServlet extends HttpServlet {
    private static final int WORKLOAD = 12;

    public static String hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(WORKLOAD);
        String hashedPassword = BCrypt.hashpw(password_plaintext, salt);

        return (hashedPassword);
    }

    private static final GenericDao<Student> studentDao = new StudentDaoImpl();
    private static final GenericDao<Teacher> teacherDao = new TeacherDaoImpl();
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> statusList = new ArrayList<>(Arrays.asList("student", "teacher"));

        req.setAttribute("statuses", statusList);
        req.getRequestDispatcher("/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("register".equals(action)) {
            try {
                String firstName = req.getParameter("firstName");
                String lastName = req.getParameter("lastName");
                String login = req.getParameter("login");
                String password = req.getParameter("password");
                String status = req.getParameter("status");

                List<Student> students = studentDao.findAll();
                List<Teacher> teachers = teacherDao.findAll();
                int previousStudentId = students.get(students.size() - 1).getId();
                int previousTeacherId = teachers.get(teachers.size() - 1).getId();
                int newStudentId = previousStudentId + 1;
                int newTeacherId = previousTeacherId + 1;
                if (firstName.isEmpty() || lastName.isEmpty() || password.isEmpty() || login.isEmpty() || !validate(login) || (password.length() < 6)) {
                    resp.sendRedirect("/registration");
                } else {
                    if (status.equals("student")) {
                        studentDao.create(new Student(newStudentId, firstName, lastName, login, hashPassword(password)));
                    } else {
                        teacherDao.create(new Teacher(newTeacherId, firstName, lastName, login, hashPassword(password)));
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        try {
            resp.sendRedirect("/login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}