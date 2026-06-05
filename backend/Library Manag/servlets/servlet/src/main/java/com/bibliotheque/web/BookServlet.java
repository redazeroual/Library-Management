package com.bibliotheque.web;

import com.bibliotheque.model.Book;
import com.bibliotheque.service.BookService;
import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/api/books")
public class BookServlet {
    private BookService bookService = new BookService();
    private Gson gson = new Gson();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Book> books = bookService.getCatalog();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(gson.toJson(books));
    }
}