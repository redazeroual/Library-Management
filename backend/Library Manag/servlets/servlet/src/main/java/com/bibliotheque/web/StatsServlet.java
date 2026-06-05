package com.bibliotheque.web;

import com.bibliotheque.dao.impl.BookDAOImpl;
import com.bibliotheque.dao.impl.LoanDAOImpl;
import com.bibliotheque.model.DashboardStats;
import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/stats")
public class StatsServlet extends HttpServlet {
    private BookDAOImpl bookDAO = new BookDAOImpl();
    private LoanDAOImpl loanDAO = new LoanDAOImpl();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int total = bookDAO.countTotalBooks();
        int active = loanDAO.countActiveLoans();
        int overdues = loanDAO.countOverdueLoans();

        DashboardStats stats = new DashboardStats(total, active, overdues);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(gson.toJson(stats));
    }
}