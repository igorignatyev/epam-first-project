package com.epam.lab.controller.auth;

import com.epam.lab.dao.StudentDao;
import com.epam.lab.dao.StudentDaoImpl;
import com.epam.lab.dao.TeacherDao;
import com.epam.lab.dao.TeacherDaoImpl;
import com.epam.lab.entity.Student;
import com.epam.lab.entity.Teacher;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginServlet extends HttpServlet {
    TeacherDao teacherDao = new TeacherDaoImpl();
    StudentDao studentDao = new StudentDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> localesList = new ArrayList<>(Arrays.asList("En", "Ru"));
        req.setAttribute("locales", localesList);
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("login".equals(action)) {
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String locale = req.getParameter("locale");


            if (teacherDao.getHashedPasswordByEmail(email) != null) {
                String hashedPassword = teacherDao.getHashedPasswordByEmail(email);

                if (checkPassword(password, hashedPassword)) {
                    HttpSession oldSession = req.getSession(false);
                    if (oldSession != null) {
                        oldSession.invalidate();
                    }

                    HttpSession session = req.getSession(true);
                    session.setAttribute("email", email);
                    session.setAttribute("role", "teacher");
                    Teacher teacher = teacherDao.findByEmail(email);

                    resp.sendRedirect("/teacher/teacher?teacherId=" + teacher.getId());
                }
            } else if (studentDao.getHashedPasswordByEmail(email) != null) {
                String hashedPassword = studentDao.getHashedPasswordByEmail(email);
                if (checkPassword(password, hashedPassword)) {
                    HttpSession oldSession = req.getSession(false);
                    if (oldSession != null) {
                        oldSession.invalidate();
                    }

                    HttpSession session = req.getSession(true);
                    session.setAttribute("email", email);
                    session.setAttribute("role", "student");
                    Student student = studentDao.findByEmail(email);

                    resp.sendRedirect("/student/student?studentId=" + student.getId());
                }
            } else if ("admin".equals(email) && "admin".equals(password)) {
                HttpSession oldSession = req.getSession(false);
                if (oldSession != null) {
                    oldSession.invalidate();
                }

                HttpSession session = req.getSession(true);
                session.setAttribute("email", email);
                session.setAttribute("role", "admin");

                resp.sendRedirect("/admin");
            }
        }
    }


    private static boolean checkPassword(String passwordPlaintext, String storedHash) {
        if (null == storedHash || !storedHash.startsWith("$2a$")) {
            System.out.println("Something's wrong");
        }
        return (BCrypt.checkpw(passwordPlaintext, storedHash));

    }
}