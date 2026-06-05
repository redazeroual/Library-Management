package com.bibliotheque.web;

import com.bibliotheque.dao.UserDAO;
import com.bibliotheque.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/api/login")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        User authenticatedUser = userDAO.checkLogin(user, pass);

        if (authenticatedUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", authenticatedUser);

            response.getWriter().print("{\"status\": \"success\"}");
        } else {
            response.setStatus(401);
            response.getWriter().print("{\"status\": \"error\", \"message\": \"Identifiants incorrects\"}");
        }
    }

}
