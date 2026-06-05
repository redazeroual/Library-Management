package com.bibliotheque.web;

import com.bibliotheque.dao.LoanDAO;
import com.bibliotheque.service.LoanService;
import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/loans")
public class LoanServlet extends HttpServlet {
    private LoanService loanService = new LoanService();
    private Gson gson = new Gson();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LoanDAO dao = new LoanDAO();
        response.setContentType("application/json");
        response.getWriter().print(gson.toJson(dao.getAllLoans()));
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int memberId = Integer.parseInt(request.getParameter("memberId"));
        String dateStr = request.getParameter("dueDate");

        java.sql.Date dueDate = java.sql.Date.valueOf(dateStr);

        String result = loanService.processNewLoan(bookId, memberId, dueDate);


        response.setContentType("application/json");
        if (result.startsWith("Succès")) {
            response.setStatus(200);
            response.getWriter().print("{\"message\": \"" + result + "\"}");
        } else {
            response.setStatus(400);
            response.getWriter().print("{\"error\": \"" + result + "\"}");
        }
    }
}