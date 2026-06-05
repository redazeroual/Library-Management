package com.bibliotheque.dao;

import com.bibliotheque.model.Book;

import java.util.List;

public interface IBookDAO {
    List<Book> getAllBooks();
    boolean addBook(Book book);
    boolean deleteBook(int id);
    Book getBookById(int id);
}
