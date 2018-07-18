package com.epam.lab.error;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorHandler {
    public static void error(String message, HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("message", message);
        try {
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
            return;
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
