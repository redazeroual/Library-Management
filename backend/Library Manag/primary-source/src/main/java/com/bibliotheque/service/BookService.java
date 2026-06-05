package com.bibliotheque.service;

import com.bibliotheque.dao.BookDAO;
import com.bibliotheque.model.Book;

import java.util.List;

public class BookService {
    private BookDAO bookDAO = new BookDAO();

    public List<Book> getCatalog() {
        return bookDAO.getAllBooks();
    }

    public boolean addNewBook(Book book) {
        return bookDAO.addBook(book);
    }
}
