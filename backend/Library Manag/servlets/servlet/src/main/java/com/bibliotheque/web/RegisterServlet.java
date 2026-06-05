package com.bibliotheque.web;

import com.bibliotheque.dao.impl.UserDAOImpl;
import com.bibliotheque.dao.IUserDAO;
import com.bibliotheque.model.User;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/api/register")
public class RegisterServlet extends HttpServlet {

    private IUserDAO userDAO = new UserDAOImpl();
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Map<String, String> responseData = new HashMap<>();


        String fullName = request.getParameter("fullName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");


        if (username == null || password == null || fullName == null || username.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseData.put("message", "Tous les champs sont obligatoires.");
            response.getWriter().print(gson.toJson(responseData));
            return;
        }


        if (userDAO.existsByUsername(username)) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            responseData.put("message", "Cet identifiant est déjà utilisé.");
            response.getWriter().print(gson.toJson(responseData));
            return;
        }


        User newUser = new User(0, username, password, fullName);
        boolean success = userDAO.addUser(newUser);

        if (success) {
            response.setStatus(HttpServletResponse.SC_CREATED);
            responseData.put("message", "Compte créé avec succès !");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            responseData.put("message", "Erreur lors de l'enregistrement en base de données.");
        }


        response.getWriter().print(gson.toJson(responseData));
    }
}