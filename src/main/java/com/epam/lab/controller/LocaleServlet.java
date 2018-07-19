package com.epam.lab.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LocaleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        session.setAttribute("locale", req.getParameter("locale"));
        String redirect_to = req.getParameter("redirect_to");
        if (redirect_to != null && !redirect_to.isEmpty()) {
            resp.sendRedirect(redirect_to);
        } else {
            resp.sendRedirect(req.getContextPath());
        }
    }
}
