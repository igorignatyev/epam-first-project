package com.epam.lab.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        System.out.println("session in AdminServlet " + session);

        if (session != null) {
            String role = (String) session.getAttribute("role");

            System.out.println("role in AdminServlet " + role);

            if ("admin".equals(role)) {
                req.getRequestDispatcher("/admin.jsp").forward(req, resp);
            } else {
                accessDenied(resp);
            }
        }
    }

    private void accessDenied(HttpServletResponse resp) throws IOException {
        resp.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        super.doPost(req, resp);
    }


}

